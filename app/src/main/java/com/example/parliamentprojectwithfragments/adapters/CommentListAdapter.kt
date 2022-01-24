package com.example.parliamentprojectwithfragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.data.Comment
import com.example.parliamentprojectwithfragments.data.CommentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//This is an adapter for RecyclerView of Comment Fragment

class CommentListAdapter(private val data: List<Comment>): RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val commentComment: TextView = view.findViewById(R.id.commentTxtV)
        val commentRating: TextView = view.findViewById(R.id.ratingTxtV)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteCommentBtn)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.comment_list_layout, parent, false)
        return ViewHolder(v)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentComment.text = data[position].comment
        holder.commentRating.text = "Rating: ${data[position].rating}"
        holder.deleteButton.setOnClickListener {
            GlobalScope.launch {
                CommentDatabase.getInstance().commentDAO.delete(data[position].id)
            }
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}