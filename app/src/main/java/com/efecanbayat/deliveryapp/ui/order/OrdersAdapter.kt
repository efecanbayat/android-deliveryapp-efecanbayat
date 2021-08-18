package com.efecanbayat.deliveryapp.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.Order
import com.efecanbayat.deliveryapp.databinding.ItemOrderBinding

class OrdersAdapter: RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    private var list = ArrayList<Order>()

    inner class OrdersViewHolder(val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.orderFoodImageView.context)
            .load(item.orderFoodImage)
            .into(holder.binding.orderFoodImageView)

        holder.binding.orderFoodNameTextView.text = item.orderFoodName
        holder.binding.orderFoodIngredientsTextView.text = item.orderFoodIngredients
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOrderList(list: List<Order>){
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }
}