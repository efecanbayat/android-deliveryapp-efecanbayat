package com.efecanbayat.deliveryapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.order.Order
import com.efecanbayat.deliveryapp.databinding.ItemOrderBinding

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    private var list = ArrayList<Order>()

    inner class OrderViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.orderRestaurantImageView.context)
            .load(item.restaurant.image)
            .into(holder.binding.orderRestaurantImageView)

        holder.binding.orderRestaurantNameTextView.text = item.restaurant.name

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOrderList(list: List<Order>) {
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }
}