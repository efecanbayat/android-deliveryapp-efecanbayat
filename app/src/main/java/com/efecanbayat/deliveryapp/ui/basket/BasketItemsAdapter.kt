package com.efecanbayat.deliveryapp.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.basket.BasketItem
import com.efecanbayat.deliveryapp.databinding.ItemBasketItemBinding

class BasketItemsAdapter : RecyclerView.Adapter<BasketItemsAdapter.OrdersViewHolder>() {

    private var basketItemList = ArrayList<BasketItem>()

    inner class OrdersViewHolder(val binding: ItemBasketItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemBasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = basketItemList[position]

        holder.binding.apply {

            Glide.with(itemFoodImageView.context)
                .load(item.foodImage)
                .into(itemFoodImageView)

            itemFoodNameTextView.text = item.foodName
            itemFoodPriceTextView.text = "${item.foodPrice} ₺"
        }
    }

    override fun getItemCount(): Int {
        return basketItemList.size
    }

    fun setBasketItemList(list: ArrayList<BasketItem>) {
        this.basketItemList = list
        notifyDataSetChanged()
    }
}