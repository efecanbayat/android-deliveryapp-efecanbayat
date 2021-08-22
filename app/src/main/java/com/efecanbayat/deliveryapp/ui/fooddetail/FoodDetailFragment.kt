package com.efecanbayat.deliveryapp.ui.fooddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.databinding.FragmentFoodDetailBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private val args: FoodDetailFragmentArgs by navArgs()
    private val viewModel: FoodDetailViewModel by viewModels()
    private val ingredientAdapter = IngredientsAdapter()
    private lateinit var food:Food

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addListeners()
    }

    private fun init() {

        viewModel.getFoodDetails(args.foodId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.itemsConstraintLayout.visibility = View.GONE
                    binding.foodProgressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.itemsConstraintLayout.visibility = View.VISIBLE
                    binding.foodProgressBar.visibility = View.GONE
                    val food = it.data!!.data

                    Glide.with(requireContext())
                        .load(food.image)
                        .into(binding.foodImageView)
                    binding.foodNameTextView.text = food.name
                    binding.totalTextView.text = "${food.price} ₺"

                    this.food = food
                    ingredientAdapter.setIngredientList(food.ingredients)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ingredientsRecyclerView.adapter = ingredientAdapter


    }

    private fun addListeners() {
        binding.addToBasketButton.setOnClickListener {
            viewModel.addToBasket(BasketItem(0,args.restaurantId,food.id,food.name,args.restaurantName,food.price,food.image))
            Toast.makeText(requireContext(), "Added to basket", Toast.LENGTH_SHORT).show()
        }
    }
}