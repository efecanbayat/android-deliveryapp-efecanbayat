package com.efecanbayat.deliveryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.Category
import com.efecanbayat.deliveryapp.data.entity.Restaurant
import com.efecanbayat.deliveryapp.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        fetchData()
    }

    private fun fetchData() {
        val restaurant = Restaurant("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/1200px-Burger_King_logo_%281999%29.svg.png","Burger King","Nilüfer")
        val restaurant3 = Restaurant("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/1200px-Burger_King_logo_%281999%29.svg.png","Burger King","Nilüfer")
        val restaurant5 = Restaurant("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/1200px-Burger_King_logo_%281999%29.svg.png","Burger King","Nilüfer")
        val restaurant7 = Restaurant("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/1200px-Burger_King_logo_%281999%29.svg.png","Burger King","Nilüfer")
        val restaurant2 = Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg","Papa John's","Yıldırım")
        val restaurant4 = Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg","Papa John's","Yıldırım")
        val restaurant6 = Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg","Papa John's","Yıldırım")
        val restaurant8 = Restaurant("https://media-cdn.tripadvisor.com/media/photo-s/1a/fe/be/14/papa-john-s-azerbaijan.jpg","Papa John's","Yıldırım")
        val restaurantList = ArrayList<Restaurant>()
        restaurantList.add(restaurant)
        restaurantList.add(restaurant2)
        restaurantList.add(restaurant3)
        restaurantList.add(restaurant4)
        restaurantList.add(restaurant5)
        restaurantList.add(restaurant6)
        restaurantList.add(restaurant7)
        restaurantList.add(restaurant8)

        restaurantAdapter.setRestaurantList(restaurantList)

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