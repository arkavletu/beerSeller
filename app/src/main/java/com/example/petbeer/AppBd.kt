package com.example.petbeer


import android.content.Context
import androidx.room.*

@Database(
    entities = [BeerEntity::class],

    version = 1
)
abstract class AppBd: RoomDatabase() {
    abstract val beerActions: BeerActions

    companion object {
        @Volatile
        private var instance: AppBd? = null

        fun getInstance(context: Context): AppBd {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,AppBd::class.java,"AppBd"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}