package com.example.parliamentprojectwithfragments.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.adapters.CommentListAdapter
import com.example.parliamentprojectwithfragments.databinding.FragmentCommentBinding
import com.example.parliamentprojectwithfragments.viewModels.CommentViewModel

class CommentFragment : Fragment() {

    private val viewModel: CommentViewModel by lazy {
        ViewModelProvider(this).get(CommentViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding lifecycle & viewModel
        val binding = DataBindingUtil.inflate<FragmentCommentBinding>(inflater, R.layout.fragment_comment, container, false)
        binding.lifecycleOwner = this
        binding.memberCommentViewModel = viewModel

        //Arguments passed by previous fragment (member detail fragment)
        val nameArg = arguments?.get("nameKey")
        val numberArg = arguments?.get("numberKey")

        //Comment Fragment heading
        binding.commentPageTxtV.text = "Comment page of ${nameArg.toString()}"

        //Getting comments of the parliament by function in viewModel
        viewModel.getCommentOfParliament(numberArg.toString().toInt())

        //Observer the comments retrieved and update Recyclerview
        viewModel.commentList.observe(viewLifecycleOwner, {
            binding.commentList.layoutManager = LinearLayoutManager(activity)
            binding.commentList.adapter = CommentListAdapter(it)
        })

        //Submitting the comment
        binding.submitBtn.setOnClickListener {
            //Does not function if EditText is empty
            if (binding.commentET.text.toString() == "") {
                Toast.makeText(activity, "Invalid comment", Toast.LENGTH_SHORT).show()
            }
            else {
                //Add comment to comment database
                viewModel.addComment(numberArg.toString().toInt(), binding.commentET.text.toString(), binding.ratingBar.rating.toDouble())
                //Resetting editText and ratingBar
                binding.commentET.text = null
                binding.ratingBar.rating = 0.0.toFloat()
                //Update the recyclerView below
                viewModel.getCommentOfParliament(numberArg.toString().toInt())
                viewModel.commentList.observe(viewLifecycleOwner, {
                    binding.commentList.layoutManager = LinearLayoutManager(activity)
                    binding.commentList.adapter = CommentListAdapter(it)
                })
            }
        }
        return binding.root
    }
}