package com.example.parliamentprojectwithfragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.network.ParliamentProperty

//This is an adapter for RecyclerView of MemberList Fragment

class MemberListAdapter (private val data: List<ParliamentProperty>): RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val memberImage: ImageView = view.findViewById(R.id.memberImageV)
        val memberName: TextView = view.findViewById(R.id.memberTxtV)
        val memberNumber: TextView = view.findViewById(R.id.memberNumberTxtV)
        init {
            view.setOnClickListener {
                val memberArg: String = memberName.text.toString()
                val bundle = bundleOf("memberKey" to memberArg)

                view.findNavController().navigate(R.id.action_memberListFragment_to_memberDetailFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.member_list_layout, parent, false)
        return ViewHolder(v)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uri = ("https://avoindata.eduskunta.fi/" + data[position].picture).toUri().buildUpon().scheme("https").build()
        Glide.with(holder.memberImage.context).load(uri).into(holder.memberImage)
        holder.memberName.text = "${data[position].first} ${data[position].last}"
        holder.memberNumber.text = "${data[position].personNumber}"
    }
    override fun getItemCount(): Int {
        return data.size
    }
}