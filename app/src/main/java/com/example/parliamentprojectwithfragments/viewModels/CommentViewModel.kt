package com.example.parliamentprojectwithfragments.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parliamentprojectwithfragments.data.Comment
import com.example.parliamentprojectwithfragments.data.CommentDatabase
import com.example.parliamentprojectwithfragments.repository.CommentRepository
import kotlinx.coroutines.launch

//ViewModel for CommentFragment

class CommentViewModel: ViewModel() {

    private val repository = CommentRepository
    private val _commentList = MutableLiveData<List<Comment>>()

    val commentList: LiveData<List<Comment>>
        get() = _commentList

    //getting comment of parliament by personNumber passed from fragment
    fun getCommentOfParliament(personNumber: Int) {
        repository.allComment.observeForever{ it ->
            _commentList.value = it.filter { it.personNumber == personNumber }
        }
    }
    //adding comment into comment database
    fun addComment(personNumber: Int, comment: String, rating: Double) {
        viewModelScope.launch {
            CommentDatabase.getInstance().commentDAO.insert(Comment(0, personNumber, comment, rating))
        }
    }
}