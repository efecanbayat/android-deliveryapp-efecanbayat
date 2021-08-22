package com.efecanbayat.deliveryapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.databinding.ItemOrderDetailBinding

class OrderDetailsAdapter: RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailViewHolder>() {

    private var foodList = ArrayList<Food>()

    inner class OrderDetailViewHolder(val binding: ItemOrderDetailBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder {
        val binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return OrderDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val food = foodList[position]

        holder.binding.apply {

            Glide.with(orderFoodImageView.context)
                .load(food.image)
                .into(orderFoodImageView)

            orderFoodNameTextView.text = food.name
            orderFoodPriceTextView.text = "${food.price} ₺"
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setOrderDetailList(foodList: ArrayList<Food>){
        this.foodList = foodList
        notifyDataSetChanged()
    }
}