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
        initCategoriesSpinner()
        initPaymentMethodsSpinner()
        addListeners()
    }

    private fun initCategoriesSpinner() {

        val categories = resources.getStringArray(R.array.Categories)
        val categoryAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, categories
        )
        binding.categorySpinner.adapter = categoryAdapter
    }

    private fun initPaymentMethodsSpinner() {

        val paymentMethods = resources.getStringArray(R.array.PaymentMethods)
        val paymentMethodAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, paymentMethods
        )
        binding.paymentSpinner.adapter = paymentMethodAdapter
    }

    private fun addListeners() {

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

            val isNull = nullCheck(
                restaurantImage,
                restaurantName,
                restaurantCategory,
                restaurantDistrict,
                restaurantAddress,
                deliveryInfo,
                deliveryTime,
                minDeliveryFee,
                paymentMethod
            )

            if (!isNull){
                Toast.makeText(requireContext(), "Please fill in all fields ", Toast.LENGTH_SHORT).show()
            }else{
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
                            binding.progressBar.visibility = View.VISIBLE
                            binding.itemsConstraintLayout.visibility = View.GONE
                        }
                        Resource.Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            binding.itemsConstraintLayout.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Restaurant Added", Toast.LENGTH_SHORT)
                                .show()
                            dismiss()
                            findNavController().navigate(R.id.action_homeFragment_self)
                        }
                        Resource.Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT)
                                .show()
                            dismiss()
                        }
                    }
                })
            }

            
        }
    }

    private fun nullCheck(
        restaurantImage: String,
        restaurantName: String,
        restaurantCategory: String,
        restaurantDistrict: String,
        restaurantAddress: String,
        deliveryInfo: String,
        deliveryTime: String,
        minDeliveryFee: String,
        paymentMethod: String,
    ): Boolean {
        when {
            restaurantImage.isEmpty() -> {
                return false
            }
            restaurantName.isEmpty() -> {
                return false
            }
            restaurantCategory.isEmpty() -> {
                return false
            }
            restaurantDistrict.isEmpty() -> {
                return false
            }
            restaurantAddress.isEmpty() -> {
                return false
            }
            deliveryInfo.isEmpty() -> {
                return false
            }
            deliveryTime.isEmpty() -> {
                return false
            }
            minDeliveryFee.isEmpty() -> {
                return false
            }
            paymentMethod.isEmpty() -> {
                return false
            }
            else -> return true
        }
    }
}