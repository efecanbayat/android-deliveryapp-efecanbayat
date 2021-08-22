package com.efecanbayat.deliveryapp.ui.restaurantdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.Ingredient
import com.efecanbayat.deliveryapp.data.entity.foodadd.FoodAddRequest
import com.efecanbayat.deliveryapp.databinding.FragmentFoodAddBinding
import com.efecanbayat.deliveryapp.utils.Resource
import com.google.android.flexbox.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodAddFragment(val restaurantId: String): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFoodAddBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var ingredientsList: ArrayList<Ingredient>
    private lateinit var ingredientAdapter: IngredientsAdapter
    private lateinit var layoutManager: FlexboxLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addListener()
    }

    private fun addListener() {
        binding.addFoodIngredientImageView.setOnClickListener {
            addFoodIngredient()
        }
        binding.addButton.setOnClickListener {
            val imageUrl = binding.imageUrlEditText.editText?.text.toString()
            val foodName = binding.nameEditText.editText?.text.toString()
            val foodDescription = binding.descriptionEditText.editText?.text.toString()
            val foodPrice = binding.priceEditText.editText?.text.toString().toDouble()
            val ingredientNames = ArrayList<String>()
            for (ingredient in ingredientsList) {
                ingredientNames.add(ingredient.ingredient)
            }

            viewModel.addFood(restaurantId, FoodAddRequest(imageUrl,foodName,foodDescription,foodPrice,ingredientNames)).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "Food Added", Toast.LENGTH_SHORT).show()
                        dismiss()
                        val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentSelf(restaurantId)
                        findNavController().navigate(action)
                    }
                    Resource.Status.ERROR -> {
                        Log.v("error","$it")
                    }
                }
            })
        }
    }

    private fun addFoodIngredient() {
        if (!binding.ingredientsEditText.editText?.text.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(binding.ingredientsEditText.editText?.text.toString(),true))
            ingredientAdapter.notifyDataSetChanged()
            binding.ingredientsEditText.editText?.text!!.clear()
        }
    }

    private fun init() {

        ingredientsList = arrayListOf()

        ingredientAdapter = IngredientsAdapter(ingredientsList)

        layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.FLEX_START

        ingredientAdapter.addListener(object: IngredientAdapterListener {
            override fun onClick(ingredient: Ingredient, position: Int) {
                ingredientsList.removeAt(position)
                ingredientAdapter.notifyDataSetChanged()
            }

        })

        binding.ingredientsRecyclerView.layoutManager = layoutManager
        binding.ingredientsRecyclerView.adapter = ingredientAdapter


    }
}