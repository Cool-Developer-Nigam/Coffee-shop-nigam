package com.nigdroid.coffeeshop.Adapter


import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nigdroid.coffeeshop.Activity.ItemsListActivity

import com.nigdroid.coffeeshop.Domain.CategoryModel
import com.nigdroid.coffeeshop.R
import com.nigdroid.coffeeshop.databinding.ViewholderCatagoryBinding

class CategoryAdapter(
    val items: MutableList<CategoryModel>,
    any: OnCategoryClickListener
)
    :RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

        private lateinit var context: Context
        private var selectedPosition=-1
        private var lastSelectedPosition=-1



    interface OnCategoryClickListener {
        fun onCategoryClick(category: CategoryModel, position: Int)
    }


    // Inside CategoryAdapter.kt

//    class Viewholder(val binding: ViewholderCatagoryBinding) : RecyclerView.ViewHolder(binding.root) {
//        // You might add a bind method here later if needed
//    }

// OR if using a click listener:

    class Viewholder(internal val binding: ViewholderCatagoryBinding, private val listener: OnCategoryClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            // Bind data
            binding.titleCat.text = item.title

            binding.root.setOnClickListener {
                listener.onCategoryClick(item, adapterPosition)
            }
        }
    }

    // Inside CategoryAdapter.kt

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding = ViewholderCatagoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(
            binding,
            listener = TODO()
        ) // Or return Viewholder(binding, yourClickListener) if using a listener
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

      val item=items[position]
        holder.binding.titleCat.text=item.title

        holder.binding.root.setOnClickListener {

            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent= Intent(context, ItemsListActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)

                }
                ContextCompat.startActivity(context,intent,null)
            },500)

        }
        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_background)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }
        else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_background)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.DarkBrown))

        }

    }

    override fun getItemCount(): Int =items.size


}

