package info.erulinman.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import info.erulinman.recyclerview.adapter.AddButtonAdapter
import info.erulinman.recyclerview.adapter.CustomItemTouchHelper
import info.erulinman.recyclerview.adapter.IndentItemDecoration
import info.erulinman.recyclerview.adapter.ItemAdapter
import info.erulinman.recyclerview.databinding.RecyclerViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: RecyclerViewBinding

    private val viewModel by lazy {
        val factory = MainViewModel.Factory((application as App).repository)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = ItemAdapter(viewModel) { msg ->
            makeToast(msg)
        }
        val addButtonAdapter = AddButtonAdapter { viewModel.addItem() }
        val concatAdapter = ConcatAdapter(itemAdapter, addButtonAdapter)


        val itemDecoration = IndentItemDecoration(R.dimen.rv_indent)

        val callback = CustomItemTouchHelper(itemAdapter)
        ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = concatAdapter
            addItemDecoration(itemDecoration)
        }

        observeViewModel(itemAdapter)
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun observeViewModel(adapter: ItemAdapter) = viewModel.apply {
        items.observe(this@MainActivity) { items ->
            if (items == null) return@observe
            adapter.submitList(items)
        }
    }
}