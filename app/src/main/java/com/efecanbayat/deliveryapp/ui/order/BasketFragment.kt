package com.efecanbayat.deliveryapp.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment: Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private var orderAdapter = BasketItemsAdapter()

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
        fetchData()
    }

    private fun fetchData() {
    }

    private fun init(){

        val orderList = viewModel.getBasket()
        orderAdapter.setOrderList(orderList)

        binding.orderRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.orderRecyclerView.adapter = orderAdapter
    }
}