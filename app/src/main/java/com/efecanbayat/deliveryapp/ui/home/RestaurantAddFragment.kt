package com.efecanbayat.deliveryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.restaurantadd.RestaurantAddRequest
import com.efecanbayat.deliveryapp.databinding.FragmentRestaurantAddBinding
import com.efecanbayat.deliveryapp.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class RestaurantAddFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRestaurantAddBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        val categories = resources.getStringArray(R.array.Categories)
        val categoryAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, categories
        )
        binding.categorySpinner.adapter = categoryAdapter

        val paymentMethods = resources.getStringArray(R.array.PaymentMethods)
        val paymentMethodAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, paymentMethods
        )
        binding.paymentSpinner.adapter = paymentMethodAdapter

        binding.addButton.setOnClickListener {

            val restaurantImage = binding.imageUrlEditText.editText?.text.toString()
            val restaurantName = binding.nameEditText.editText?.text.toString()
            val restaurantCategory = binding.categorySpinner.selectedItem.toString()
            val restaurantDistrict = binding.districtEditText.editText?.text.toString()
            val restaurantAddress = binding.addressEditText.editText?.text.toString()
            val deliveryInfo = binding.deliveryInfoEditText.editText?.text.toString()
            val deliveryTime = binding.deliveryTimeEditText.editText?.text.toString()
            val minDeliveryFee = binding.minDeliveryFeeEditText.editText?.text.toString()
            val paymentMethod = binding.categorySpinner.selectedItem.toString()
            val rating = Random.nextInt(5, 10)

            viewModel.addRestaurant(
                RestaurantAddRequest(
                    restaurantImage,
                    restaurantName,
                    restaurantCategory,
                    restaurantDistrict,
                    restaurantAddress,
                    deliveryInfo,
                    deliveryTime,
                    minDeliveryFee,
                    paymentMethod,
                    rating
                )
            ).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "Restaurant Added", Toast.LENGTH_SHORT).show()
                        dismiss()
                        findNavController().navigate(R.id.action_homeFragment_self)
                    }
                    Resource.Status.ERROR -> {

                    }
                }
            })
        }
    }
}