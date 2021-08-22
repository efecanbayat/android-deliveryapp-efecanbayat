package com.efecanbayat.deliveryapp.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.databinding.FragmentBasketBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment: Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private var basketItemAdapter = BasketItemsAdapter()
    private var foodIdsList = ArrayList<String>()
    private lateinit var restaurantId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        val orderList = viewModel.getBasket() as ArrayList<BasketItem>
        basketItemAdapter.setBasketItemList(orderList)
        viewModel.itemList = orderList

        binding.orderRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.orderRecyclerView.adapter = basketItemAdapter

        binding.clearButton.setOnClickListener {
            viewModel.deleteFromBasket(viewModel.itemList)
            Toast.makeText(requireContext(), "Basket Cleared", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_basketFragment_self)
        }

        binding.orderButton.setOnClickListener {

            for (item in viewModel.getBasket() as ArrayList<BasketItem>) {
                foodIdsList.add(item.foodId)
                restaurantId = item.restaurantId
            }
            viewModel.postBulkOrder(restaurantId,foodIdsList).observe(viewLifecycleOwner, {
                when(it.status){
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        viewModel.deleteFromBasket(viewModel.itemList)
                        Toast.makeText(requireContext(), "Order Successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_basketFragment_self)
                    }
                    Resource.Status.ERROR -> {

                    }
                }
            })
        }
    }
}