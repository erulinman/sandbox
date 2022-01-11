package info.erulinman.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import info.erulinman.recyclerview.databinding.RecyclerViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: RecyclerViewBinding

    private var items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = ItemAdapter { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        val addButtonAdapter = AddButtonAdapter { addItem(itemAdapter) }
        val concatAdapter = ConcatAdapter(itemAdapter, addButtonAdapter)

        binding.recyclerView.adapter = concatAdapter
    }

    private fun addItem(itemAdapter: ItemAdapter) {
        val newItem = Item(items.size + 1)
        val newList = mutableListOf<Item>().apply {
            addAll(items)
            add(newItem)
        }
        items = newList
        itemAdapter.submitList(items)
    }

}