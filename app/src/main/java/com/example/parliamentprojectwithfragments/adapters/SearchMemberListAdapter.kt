package com.example.parliamentprojectwithfragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.network.ParliamentProperty

//This is an adapter for RecyclerView of SearchFragment

class SearchMemberListAdapter (private val data: List<ParliamentProperty>): RecyclerView.Adapter<SearchMemberListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val memberName: TextView = view.findViewById(R.id.searchMemberNameTxtV)
        val memberNumber: TextView = view.findViewById(R.id.searchMemberNumberTxtV)
        init {
            view.setOnClickListener {
                val memberArg: String = memberName.text.toString()
                val bundle = bundleOf("memberKey" to memberArg)

                view.findNavController().navigate(R.id.action_searchFragment_to_memberDetailFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_member_list_layout, parent, false)
        return ViewHolder(v)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memberName.text = "${data[position].first} ${data[position].last}"
        holder.memberNumber.text = "${data[position].personNumber}"
    }
    override fun getItemCount(): Int {
        return data.size
    }
}