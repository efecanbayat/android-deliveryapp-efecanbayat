package com.efecanbayat.deliveryapp.ui.profile

import com.efecanbayat.deliveryapp.data.entity.order.Order

interface IOrderOnClickListener {
    fun onClick(order: Order)
}