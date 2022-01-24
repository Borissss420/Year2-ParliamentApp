package com.example.parliamentprojectwithfragments.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentprojectwithfragments.MyApp
import com.example.parliamentprojectwithfragments.network.ParliamentDAO
import com.example.parliamentprojectwithfragments.network.ParliamentProperty

//Database for storing Parliaments

@Database(entities = [ParliamentProperty::class], version = 1, exportSchema = false)
abstract class ParliamentDatabase: RoomDatabase() {
    abstract val parliamentDAO: ParliamentDAO
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDatabase? = null
        fun getInstance(): ParliamentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        ParliamentDatabase::class.java, "parliament_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
