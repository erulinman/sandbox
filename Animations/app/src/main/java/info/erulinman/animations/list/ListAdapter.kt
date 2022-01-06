package info.erulinman.animations.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.erulinman.animations.databinding.ItemBinding

class ListAdapter(private val onItemClick: (Item?) -> Unit) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    private val items: List<Item> = listOf(
        Item("AnimationUtils")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.tvItemName.text = item.name
            itemView.setOnClickListener { onItemClick(item) }
        }
    }
}