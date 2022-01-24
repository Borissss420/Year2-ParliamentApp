package com.example.parliamentprojectwithfragments.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentprojectwithfragments.MyApp

//Database for storing comments

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentDatabase: RoomDatabase() {
    abstract val commentDAO: CommentDAO
    companion object {
        @Volatile
        private var INSTANCE: CommentDatabase? = null
        fun getInstance(): CommentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        CommentDatabase::class.java, "comment_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}