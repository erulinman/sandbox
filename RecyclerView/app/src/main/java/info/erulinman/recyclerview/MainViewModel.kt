package info.erulinman.recyclerview

import androidx.lifecycle.*
import info.erulinman.recyclerview.data.Item
import info.erulinman.recyclerview.data.ItemsDao
import info.erulinman.recyclerview.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val items = repository.items

    fun addItem() = viewModelScope.launch(Dispatchers.IO) {
        val position = repository.getMaxPosition()?.plus(1) ?: 0
        val item = Item(position = position)
        repository.addItem(item)
    }

    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.IO) { repository.deleteItem(item) }

    class Factory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(repository) as T
            }
            error("Wrong ViewModel type")
        }
    }
}