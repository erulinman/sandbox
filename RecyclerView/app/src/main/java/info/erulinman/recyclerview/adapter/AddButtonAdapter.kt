package info.erulinman.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.erulinman.recyclerview.databinding.AddButtonBinding

class AddButtonAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<AddButtonAdapter.AddButtonViewHolder>() {

    class AddButtonViewHolder(binding: AddButtonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onClick: () -> Unit) {
            itemView.setOnClickListener { onClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddButtonViewHolder {
        val binding = AddButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AddButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddButtonViewHolder, position: Int) {
       holder.bind(onClick)
    }

    override fun getItemCount() = 1
}