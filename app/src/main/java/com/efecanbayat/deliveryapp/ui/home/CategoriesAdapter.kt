package com.efecanbayat.deliveryapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.data.entity.Category
import com.efecanbayat.deliveryapp.databinding.ItemCategoryBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var list = ArrayList<Category>()
    private var listener: ICategoryOnClickListener? = null
    private var selectedItem = 0

    inner class CategoriesViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.categoryImageView.context)
            .load(item.categoryImage).into(holder.binding.categoryImageView)

        holder.binding.categoryNameTextView.text = item.categoryName


        holder.itemView.setOnClickListener {
            selectedItem = holder.adapterPosition
            listener?.onClick(item)
            notifyDataSetChanged()
        }

        if (selectedItem == position) {
            holder.binding.categoryCardView.setCardBackgroundColor(Color.GREEN)
        }else {
            holder.binding.categoryCardView.setCardBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setCategoryList(list: List<Category>) {
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }

    fun addListener(listener: ICategoryOnClickListener) {
        this.listener = listener
    }
}