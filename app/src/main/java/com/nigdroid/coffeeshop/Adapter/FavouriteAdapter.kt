package com.nigdroid.coffeeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nigdroid.coffeeshop.Domain.ItemsModel
import com.nigdroid.coffeeshop.Helper.ManagementFavourite // Keep if needed for removal
import com.nigdroid.coffeeshop.databinding.ViewholderFavouriteBinding // You'll need a layout for each favourite item

class FavouriteAdapter(
    private val favouriteItems: ArrayList<ItemsModel>,
    private val context: Context
    // You could add a listener here for removing items from the favourite list
    // private val changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    // You'll likely need a ManagementFavourite instance if you allow removal from the list
    // private val managementFavourite = ManagementFavourite(context)

    class ViewHolder(val binding: ViewholderFavouriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favouriteItems[position]

        holder.binding.apply {
            titleTxt.text = item.title
            priceTxt.text = "$${item.price}"

            // Load the item image (assuming picUrl is a list and you want the first one)
            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(pic)

            // Optional: Handle removing from favourites
            // deleteBtn.setOnClickListener {
            //    managementFavourite.removeItem(item)
            //    favouriteItems.removeAt(position)
            //    notifyItemRemoved(position)
            //    notifyItemRangeChanged(position, favouriteItems.size)
            //    changeNumberItemsListener?.onChanged() // Notify if listener is provided
            // }
        }
    }

    override fun getItemCount(): Int = favouriteItems.size
}