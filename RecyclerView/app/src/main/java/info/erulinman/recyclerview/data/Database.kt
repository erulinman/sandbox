package info.erulinman.recyclerview.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun itemsDao(): ItemsDao
}