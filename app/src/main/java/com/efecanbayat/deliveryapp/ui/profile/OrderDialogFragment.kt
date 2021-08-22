package com.efecanbayat.deliveryapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.databinding.FragmentOrderDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDialogFragment(val list: List<Food>): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentOrderDialogBinding
    private val orderDetailAdapter = OrderDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDialogBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addListeners()
    }

    private fun init() {

        binding.orderDetailRecylerView.layoutManager = LinearLayoutManager(context)
        binding.orderDetailRecylerView.adapter = orderDetailAdapter

        orderDetailAdapter.setOrderDetailList(ArrayList(list))
    }

    private fun addListeners() {

        binding.backButton.setOnClickListener {
            dismiss()
        }
    }
}