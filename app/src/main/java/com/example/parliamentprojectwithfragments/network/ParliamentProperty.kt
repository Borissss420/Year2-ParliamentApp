package com.example.parliamentprojectwithfragments.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.squareup.moshi.Json

//Parliament data class

@Entity
data class ParliamentProperty(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "personNumber") val personNumber: Int,
    @Json(name = "seatNumber") val seatNumber: Int = 0,
    @Json(name = "last") val last: String,
    @Json(name = "first") val first: String,
    @Json(name = "party") val party: String,
    @Json(name = "minister") val minister: Boolean = false,
    @Json(name = "picture") val picture: String = "",
    @Json(name = "twitter") val twitter: String = "",
    @Json(name = "bornYear") val bornYear: Int = 0,
    @Json(name = "constituency") val constituency: String = ""
)

@Dao
interface ParliamentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: List<ParliamentProperty>)
    @Query("select * from ParliamentProperty")
    fun getAll(): LiveData<List<ParliamentProperty>>
}