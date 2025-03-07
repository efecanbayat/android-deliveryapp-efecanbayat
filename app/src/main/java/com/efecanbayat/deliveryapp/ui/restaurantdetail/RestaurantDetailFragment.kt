package com.efecanbayat.deliveryapp.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant
import com.efecanbayat.deliveryapp.databinding.FragmentRestaurantDetailBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment(): Fragment() {
    private lateinit var binding: FragmentRestaurantDetailBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()
    private val foodAdapter = FoodsAdapter()
    private lateinit var restaurant: Restaurant

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initUser()
        addListeners()
    }

    private fun init() {

        viewModel.getRestaurantDetail(args.restaurantId).observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.restaurantCardConstaint.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.restaurantCardConstaint.visibility = View.VISIBLE
                    val restaurant = it.data!!.data

                    Glide.with(requireContext())
                        .load(restaurant.image)
                        .into(binding.restaurantImageView)
                    binding.restaurantNameTextView.text = restaurant.name
                    binding.restaurantDistrictTextView.text = restaurant.district
                    binding.restaurantRatingTextView.text = restaurant.rating
                    binding.restaurantFeeTextView.text = "${restaurant.minimumDeliveryFee} ₺"
                    binding.restaurantTimeTextView.text = restaurant.deliveryTime
                    this.restaurant = restaurant

                    foodAdapter.setFoodList(restaurant.foods)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.foodRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foodRecyclerView.adapter = foodAdapter

    }

    private fun initUser() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    val user = it.data!!.user
                    val userRole = user.role
                    isUserAdmin(userRole)
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun isUserAdmin(userRole: String) {
        if (userRole == "admin") {
            binding.addFoodButton.show()
        }else {
            binding.addFoodButton.hide()
        }
    }

    private fun addListeners() {

        binding.addFoodButton.setOnClickListener {
            val dialog = FoodAddFragment(args.restaurantId)
            dialog.show(requireActivity().supportFragmentManager, "foodAdd")
        }

        foodAdapter.addListener(object: IFoodOnClickListener{
            override fun onClick(food: Food) {
                val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFoodDetailFragment(food.id,restaurant.id,restaurant.name)
                findNavController().navigate(action)
                foodAdapter.removeListeners()
            }
        })
    }
}