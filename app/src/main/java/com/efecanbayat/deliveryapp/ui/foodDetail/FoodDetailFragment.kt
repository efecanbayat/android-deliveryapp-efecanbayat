package com.efecanbayat.deliveryapp.ui.foodDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.databinding.FragmentFoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment: Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private val ingredientAdapter = IngredientsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        fetchData()
    }

    private fun fetchData() {
        val ingredient = "Pickle"
        val ingredient2 = "Cheese"
        val ingredient3 = "Patato"
        val ingredient4 = "Tomato"
        val ingredientList = ArrayList<String>()
        ingredientList.add(ingredient)
        ingredientList.add(ingredient2)
        ingredientList.add(ingredient3)
        ingredientList.add(ingredient4)

        ingredientAdapter.setIngredientList(ingredientList)
    }

    private fun init(){

        binding.ingredientsRecylerView.layoutManager = LinearLayoutManager(context)
        binding.ingredientsRecylerView.adapter = ingredientAdapter

        Glide.with(requireContext())
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSffVLe7D0P5YzO9zNVOlrOsB8bogjvagabJg&usqp=CAU")
            .into(binding.foodImageView)
    }
}