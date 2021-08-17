package com.efecanbayat.deliveryapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.Restaurant
import com.efecanbayat.deliveryapp.databinding.ItemRestaurantBinding

class RestaurantsAdapter: RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {

    private var list = ArrayList<Restaurant>()
    private var listener: IRestaurantOnClickListener? = null

    inner class RestaurantsViewHolder(val binding: ItemRestaurantBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.restaurantImageView.context)
            .load(item.restaurantImage).into(holder.binding.restaurantImageView)

        holder.binding.restaurantNameTextView.text = item.restaurantName
        holder.binding.restaurantLocationTextView.text = item.restaurantLocation

        holder.itemView.setOnClickListener {
            listener?.onClick(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addListener(listener: IRestaurantOnClickListener){
        this.listener = listener
    }

    fun removeListeners(){
        this.listener = null
    }

    fun setRestaurantList(list: List<Restaurant>){
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }
}