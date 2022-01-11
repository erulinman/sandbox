package info.erulinman.recyclerview

import android.util.Log
import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<Item>() {

    init {
        Log.d("CHECKING", "DiffCallback.init()")
    }

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        Log.d("CHECKING", "ItemDiffCallback.areItemsTheSame(${oldItem.id == newItem.id})")
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        Log.d("CHECKING", "ItemDiffCallback.areContentsTheSame(${oldItem == newItem})")
        return oldItem == newItem
    }
}