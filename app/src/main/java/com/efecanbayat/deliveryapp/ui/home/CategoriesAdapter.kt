package com.efecanbayat.deliveryapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.Category
import com.efecanbayat.deliveryapp.databinding.ItemCategoryBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoryList = ArrayList<Category>()
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
        val category = categoryList[position]

        holder.binding.apply {

            Glide.with(categoryImageView.context)
                .load(category.categoryImage).into(categoryImageView)

            categoryNameTextView.text = category.categoryName

            if (selectedItem == position) {
                categoryCardView.setCardBackgroundColor(
                    categoryCardView.context.getColor(
                        R.color.primary
                    )
                )
            } else {
                categoryCardView.setCardBackgroundColor(
                    categoryCardView.context.getColor(
                        R.color.secondary_dark
                    )
                )
            }
        }

        holder.itemView.setOnClickListener {
            selectedItem = holder.adapterPosition
            listener?.onClick(category)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setCategoryList(categoryList: List<Category>) {
        this.categoryList = ArrayList(categoryList)
        notifyDataSetChanged()
    }

    fun addListener(listener: ICategoryOnClickListener) {
        this.listener = listener
    }
}