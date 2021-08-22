package com.efecanbayat.deliveryapp.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.food.Food
import com.efecanbayat.deliveryapp.databinding.ItemFoodBinding

class FoodsAdapter: RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {

    private var foodList = ArrayList<Food>()
    private var listener: IFoodOnClickListener? = null

    inner class FoodsViewHolder(val binding:ItemFoodBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foodList[position]

        holder.binding.apply {

            Glide.with(foodImageView.context)
                .load(food.image)
                .into(foodImageView)

            foodNameTextView.text = food.name
            foodDescriptionTextView.text = food.description
            foodPriceTextView.text = "${food.price} $"
        }

        holder.itemView.setOnClickListener {
            listener?.onClick(food)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun addListener(listener: IFoodOnClickListener){
        this.listener = listener
    }

    fun removeListeners(){
        this.listener = null
    }

    fun setFoodList(list: List<Food>){
        this.foodList = ArrayList(list)
        notifyDataSetChanged()
    }
}