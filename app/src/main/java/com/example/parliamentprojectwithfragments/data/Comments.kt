package com.example.parliamentprojectwithfragments.data

import androidx.lifecycle.LiveData
import androidx.room.*

//Comment data class

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val personNumber: Int,
    val comment: String,
    val rating: Double = 0.0
)

@Dao
interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)
    @Query("select * from Comment")
    fun getAll(): LiveData<List<Comment>>
    @Query("delete from Comment")
    fun deleteAll()
    @Query("DELETE from Comment WHERE id = :id")
    suspend fun delete(id: Int)
}