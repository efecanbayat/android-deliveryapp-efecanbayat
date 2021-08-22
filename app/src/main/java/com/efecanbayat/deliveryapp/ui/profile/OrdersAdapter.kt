package com.efecanbayat.deliveryapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.order.Order
import com.efecanbayat.deliveryapp.databinding.ItemOrderBinding

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    private var orderList = ArrayList<Order>()
    private var listener: IOrderOnClickListener? = null

    inner class OrderViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]

        holder.binding.apply {

            Glide.with(orderRestaurantImageView.context)
                .load(order.restaurant.image)
                .into(orderRestaurantImageView)

            orderRestaurantNameTextView.text = order.restaurant.name
        }
        holder.itemView.setOnClickListener {
            listener?.onClick(order)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    fun setOrderList(orderList: List<Order>) {
        this.orderList = ArrayList(orderList)
        notifyDataSetChanged()
    }

    fun addListener(listener: IOrderOnClickListener) {
        this.listener = listener
    }
}