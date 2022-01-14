package info.erulinman.recyclerview.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import info.erulinman.recyclerview.data.Item

class DiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}