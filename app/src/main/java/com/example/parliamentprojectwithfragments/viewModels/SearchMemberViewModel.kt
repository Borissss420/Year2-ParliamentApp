package com.example.parliamentprojectwithfragments.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentprojectwithfragments.data.ParliamentDatabase
import com.example.parliamentprojectwithfragments.network.ParliamentApi
import com.example.parliamentprojectwithfragments.network.ParliamentProperty
import com.example.parliamentprojectwithfragments.repository.Repository
import kotlinx.coroutines.launch

//ViewModel for SearchFragment and filtering editText(Search bar)

class SearchMemberViewModel: ViewModel() {

    private val repository = Repository
    private val _memberList = MutableLiveData<List<ParliamentProperty>>()
    private val _filterList = MutableLiveData<List<ParliamentProperty>>()

    val memberList: LiveData<List<ParliamentProperty>>
        get() = _memberList
    val filterList: LiveData<List<ParliamentProperty>>
        get() = _filterList

    init {
        getParliamentProperties()
    }
    //getting all parliaments by api service and insert them into parliament database
    private fun getParliamentProperties() {
        viewModelScope.launch {
            ParliamentDatabase.getInstance().parliamentDAO.insert(ParliamentApi.retrofitService.getProperties())
        }
        repository.allParliament.observeForever{
            _memberList.value = it
        }
    }

    //filtering all parliament names with text input from EditText
    fun filter(input: String) {
        _filterList.value = _memberList.value?.filter {("${it.first} ${it.last}").toLowerCase().contains(input.toLowerCase())}
    }
}