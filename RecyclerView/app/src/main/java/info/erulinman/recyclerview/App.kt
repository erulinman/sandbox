package info.erulinman.recyclerview

import android.app.Application
import androidx.room.Room
import info.erulinman.recyclerview.data.Database
import info.erulinman.recyclerview.data.Repository
import info.erulinman.recyclerview.data.RepositoryImpl

class App : Application() {

    private lateinit var database: Database

    private var _repository: Repository? = null
    val repository: Repository
        get() {
            checkNotNull(_repository)
            return _repository as Repository
        }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            Database::class.java,
            "database"
        ).build()

        _repository = RepositoryImpl(database.itemsDao())
    }
}