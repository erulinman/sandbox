package info.erulinman.recyclerview.data

import androidx.lifecycle.LiveData

interface Repository {

    val items: LiveData<List<Item>>

    suspend fun addItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun getMaxPosition(): Int?

}