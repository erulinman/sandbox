package info.erulinman.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.erulinman.recyclerview.MainViewModel
import info.erulinman.recyclerview.data.Item
import info.erulinman.recyclerview.databinding.ItemBinding

class ItemAdapter(
    private val viewModel: MainViewModel,
    private val onClick: (String) -> Unit
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(DiffCallback()), CustomItemTouchHelper.Adapter {

    inner class ItemViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            val name = "id #${item.id}, position: ${item.position}"
            binding.item.text = name
            itemView.setOnClickListener {
                onClick("Position: $bindingAdapterPosition")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onItemMove(from: Int, to: Int) {
        // TODO("Not yet implemented")
    }

    override fun onItemSwipe(position: Int) {
        val item = currentList[position]
        viewModel.deleteItem(item)
    }
}

