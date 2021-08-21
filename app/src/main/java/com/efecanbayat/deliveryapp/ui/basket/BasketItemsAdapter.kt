package com.efecanbayat.deliveryapp.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.databinding.ItemBasketItemBinding

class BasketItemsAdapter: RecyclerView.Adapter<BasketItemsAdapter.OrdersViewHolder>() {

    private var list = ArrayList<BasketItem>()

    inner class OrdersViewHolder(val binding: ItemBasketItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemBasketItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.itemFoodImageView.context)
            .load(item.foodImage)
            .into(holder.binding.itemFoodImageView)

        holder.binding.itemFoodNameTextView.text = item.foodName
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setBasketItemList(list: ArrayList<BasketItem>){
        this.list = list
        notifyDataSetChanged()
    }
}