package com.efecanbayat.deliveryapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.databinding.ItemOrderDetailBinding

class OrderDetailsAdapter: RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailViewHolder>() {

    private var list = ArrayList<Food>()

    inner class OrderDetailViewHolder(val binding: ItemOrderDetailBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder {
        val binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return OrderDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.orderFoodImageView.context)
            .load(item.image)
            .into(holder.binding.orderFoodImageView)

        holder.binding.orderFoodNameTextView.text = item.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOrderDetailList(list: ArrayList<Food>){
        this.list = list
        notifyDataSetChanged()
    }
}