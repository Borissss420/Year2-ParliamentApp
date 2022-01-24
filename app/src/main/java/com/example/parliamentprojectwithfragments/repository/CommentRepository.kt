package com.example.parliamentprojectwithfragments.repository

import androidx.lifecycle.LiveData
import com.example.parliamentprojectwithfragments.data.Comment
import com.example.parliamentprojectwithfragments.data.CommentDatabase

//Repository for getting comments

object CommentRepository {
    private val dao = CommentDatabase.getInstance().commentDAO
    val allComment: LiveData<List<Comment>> = dao.getAll()
}