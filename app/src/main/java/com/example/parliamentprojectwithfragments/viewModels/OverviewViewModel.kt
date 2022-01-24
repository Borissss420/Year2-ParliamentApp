package com.example.parliamentprojectwithfragments.viewModels

import androidx.lifecycle.*
import com.example.parliamentprojectwithfragments.data.ParliamentDatabase
import com.example.parliamentprojectwithfragments.repository.Repository
import com.example.parliamentprojectwithfragments.network.ParliamentApi
import com.example.parliamentprojectwithfragments.network.ParliamentProperty
import kotlinx.coroutines.launch

//ViewModel for fragments related to Parliaments (sorting party and members and getting member details)

class OverviewViewModel: ViewModel() {

    private val repository = Repository
    private val partyList = Transformations.map(repository.allParliament) { it -> it.map { it.party }.toSet()}
    private val memberOfPartyList = MutableLiveData<List<ParliamentProperty>>()
    private val _memberDetails = MutableLiveData<ParliamentProperty>()

    val party: LiveData<Set<String>>
        get() = partyList
    val memberOfParty: LiveData<List<ParliamentProperty>>
        get() = memberOfPartyList
    val memberDetails: LiveData<ParliamentProperty>
        get() = _memberDetails

    init {
        getParliamentProperties()
    }
    //Getting all parliaments by api service and insert into parliament database
    private fun getParliamentProperties() {
        viewModelScope.launch {
                ParliamentDatabase.getInstance().parliamentDAO.insert(ParliamentApi.retrofitService.getProperties())
        }
    }

    //sorting all members in a particular party
    fun getMembersInParty(party: String) {
        memberOfPartyList.value = repository.allParliament.value?.filter { it.party == party }
    }

    //getting details for particular member
    fun getMemberDetails(name: String) {
        _memberDetails.value = repository.allParliament.value?.find { "${it.first} ${it.last}" == name }
    }
}
