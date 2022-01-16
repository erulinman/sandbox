package info.erulinman.recyclerview.data

import androidx.lifecycle.LiveData

class RepositoryImpl(private val dao: ItemsDao) : Repository {

    override val items: LiveData<List<Item>> = dao.getItems()

    override suspend fun addItem(item: Item) = dao.addItem(item)

    override suspend fun deleteItem(item: Item) {
        val oldList = items.value ?: return
        val newList = mutableListOf<Item>()
        var correctPosition = 0
        oldList.forEach {
            if (it == item) return@forEach
            val newItem = Item(it.id, correctPosition)
            newList.add(newItem)
            correctPosition++
        }
        dao.deleteAndUpdateInTransaction(item, newList)
    }

    override suspend fun getMaxPosition() = dao.getMaxPosition()
}