package info.erulinman.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.erulinman.recyclerview.databinding.ItemBinding

class ItemAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(DiffCallback()) {

    inner class ItemViewHolder(
        private val binding: ItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            Log.d("CHECKING", "ItemAdapter.ItemViewHolder.bind()")
            val name = "Item #${item.id}"
            binding.item.text = name
            itemView.setOnClickListener { onClick("$name pressed") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d("CHECKING", "ItemAdapter.onCreateViewHolder()")
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("CHECKING", "ItemAdapter.onBindViewHolder()")
        holder.bind(currentList[position])
    }
}

