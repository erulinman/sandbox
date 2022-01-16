package info.erulinman.recyclerview.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items ORDER BY position;")
    fun getItems(): LiveData<List<Item>>

    @Query("SELECT MAX(position) FROM items;")
    suspend fun getMaxPosition(): Int?

    @Insert
    suspend fun addItem(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(items: List<Item>)

    @Transaction
    suspend fun deleteAndUpdateInTransaction(item: Item, items: List<Item>) {
        delete(item)
        update(items)
    }
}