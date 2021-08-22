package com.efecanbayat.deliveryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.Category
import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant
import com.efecanbayat.deliveryapp.databinding.FragmentHomeBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val restaurantAdapter = RestaurantsAdapter()
    private val categoryAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initCarousel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        fetchRestaurants()
        fetchCategories()
        addListeners()
    }

    private fun addListeners() {

        restaurantAdapter.addListener(object : IRestaurantOnClickListener {
            override fun onClick(restaurant: Restaurant) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
                restaurantAdapter.removeListeners()
            }

        })

        categoryAdapter.addListener(object : ICategoryOnClickListener {
            override fun onClick(category: Category) {
                viewModel.getRestaurantByCategory(category.categoryName)
                    .observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.restaurantRecyclerView.visibility = View.GONE
                            }
                            Resource.Status.SUCCESS -> {

                                if (category.categoryName == "All") {
                                    fetchRestaurants()
                                } else {
                                    binding.progressBar.visibility = View.GONE
                                    binding.restaurantRecyclerView.visibility = View.VISIBLE
                                    viewModel.restaurantList = it.data?.restaurantList
                                    setRestaurants(viewModel.restaurantList)
                                }

                            }
                            Resource.Status.ERROR -> {

                            }
                        }
                    })
            }

        })
    }


    private fun fetchRestaurants() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.restaurantRecyclerView.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.restaurantRecyclerView.visibility = View.VISIBLE
                    viewModel.restaurantList = it.data?.restaurantList
                    setRestaurants(viewModel.restaurantList)
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun setRestaurants(restaurantList: ArrayList<Restaurant>?) {
        restaurantAdapter.setRestaurantList(restaurantList)
    }

    private fun initCarousel() {
        val images = ArrayList<Int>()
        images.add(R.drawable.discount)
        images.add(R.drawable.discount2)
        images.add(R.drawable.discount3)
        binding.carouselView.pageCount = images.size

        binding.carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(images[position])
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    private fun fetchCategories() {

        val categoryList = ArrayList<Category>()
        categoryList.add(Category(R.drawable.ic_dish, "All"))
        categoryList.add(Category(R.drawable.ic_burger, "Burger"))
        categoryList.add(Category(R.drawable.ic_pizza, "Pizza"))
        categoryList.add(Category(R.drawable.ic_chicken, "Chicken"))
        categoryList.add(Category(R.drawable.ic_kebab, "Kebab"))
        categoryList.add(Category(R.drawable.ic_soup, "Soup"))
        categoryList.add(Category(R.drawable.ic_dessert, "Dessert"))

        categoryAdapter.setCategoryList(categoryList)
    }

    private fun init() {

        binding.restaurantRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.restaurantRecyclerView.adapter = restaurantAdapter

        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter
    }
}