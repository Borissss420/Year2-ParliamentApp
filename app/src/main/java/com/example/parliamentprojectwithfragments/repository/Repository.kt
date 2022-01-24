package com.example.parliamentprojectwithfragments.repository

import androidx.lifecycle.LiveData
import com.example.parliamentprojectwithfragments.data.ParliamentDatabase
import com.example.parliamentprojectwithfragments.network.ParliamentProperty

//Repository for getting parliaments

object Repository {
    private val dao = ParliamentDatabase.getInstance().parliamentDAO
    val allParliament: LiveData<List<ParliamentProperty>> = dao.getAll()
}