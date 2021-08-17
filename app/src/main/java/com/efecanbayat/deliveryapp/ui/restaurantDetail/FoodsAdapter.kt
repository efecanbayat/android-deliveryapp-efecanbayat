package com.efecanbayat.deliveryapp.ui.restaurantDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.Food
import com.efecanbayat.deliveryapp.databinding.ItemFoodBinding

class FoodsAdapter: RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {

    private var list = ArrayList<Food>()
    private var listener: IFoodOnClickListener? = null

    inner class FoodsViewHolder(val binding:ItemFoodBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.foodImageView.context)
            .load(item.foodImage)
            .into(holder.binding.foodImageView)

        holder.binding.foodNameTextView.text = item.foodName
        holder.binding.foodDetailTextView.text = item.foodDetail
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addListener(listener: IFoodOnClickListener){
        this.listener = listener
    }

    fun removeListeners(){
        this.listener = null
    }

    fun setFoodList(list: List<Food>){
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }
}