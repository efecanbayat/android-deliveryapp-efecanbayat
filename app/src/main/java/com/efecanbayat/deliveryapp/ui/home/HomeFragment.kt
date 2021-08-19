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
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val restaurantAdapter = RestaurantsAdapter()
    private val categoryAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        initCarousel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        fetchRestaurants()
        fetchData()
    }


    private fun fetchRestaurants() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

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
            imageView.scaleType= ImageView.ScaleType.CENTER_CROP
        }
    }

    private fun fetchData() {

        val category = Category(R.drawable.burger,"Burger")
        val category2 = Category(R.drawable.soup,"Soup")
        val category3= Category(R.drawable.chicken,"Chicken")
        val category4 = Category(R.drawable.dessert,"Dessert")
        val category5 = Category(R.drawable.kebab,"Kebab")
        val categoryList = ArrayList<Category>()
        categoryList.add(category)
        categoryList.add(category2)
        categoryList.add(category3)
        categoryList.add(category4)
        categoryList.add(category5)

        categoryAdapter.setCategoryList(categoryList)
    }

    private fun init() {



        binding.restaurantRecyclerView.layoutManager = GridLayoutManager(context,2)
        binding.restaurantRecyclerView.adapter = restaurantAdapter
        restaurantAdapter.addListener(object: IRestaurantOnClickListener{
            override fun onClick(restaurant: Restaurant) {
                findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailFragment)
                restaurantAdapter.removeListeners()
            }

        })
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecyclerView.adapter = categoryAdapter
    }
}