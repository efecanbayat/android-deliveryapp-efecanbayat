package com.efecanbayat.deliveryapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.restaurant.Restaurant
import com.efecanbayat.deliveryapp.databinding.ItemRestaurantBinding

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {

    private var restaurantlist = ArrayList<Restaurant>()
    private var listener: IRestaurantOnClickListener? = null

    inner class RestaurantsViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurantlist[position]

        holder.binding.apply {
            Glide.with(restaurantImageView.context)
                .load(restaurant.image).into(restaurantImageView)

            restaurantNameTextView.text = restaurant.name
            restaurantLocationTextView.text = restaurant.district
        }

        holder.itemView.setOnClickListener {
            listener?.onClick(restaurant)
        }

    }

    override fun getItemCount(): Int {
        return restaurantlist.size
    }

    fun addListener(listener: IRestaurantOnClickListener) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    fun setRestaurantList(restaurantlist: ArrayList<Restaurant>?) {
        restaurantlist?.let {
            this.restaurantlist = restaurantlist
            notifyDataSetChanged()
        }
    }
}