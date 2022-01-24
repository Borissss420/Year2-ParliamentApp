package com.example.parliamentprojectwithfragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentprojectwithfragments.R

//This is an adapter for RecyclerView of PartyList Fragment

class PartyListAdapter(private val data: List<String>): RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val partyImage: ImageView = view.findViewById(R.id.partyImgV)
        val partyName: TextView = view.findViewById(R.id.partyTxtV)
        init {
            view.setOnClickListener{
                val partyNameArg: String = partyName.text.toString()
                val bundle = bundleOf("partyNameKey" to partyNameArg)

                view.findNavController().navigate(R.id.action_partyListFragment_to_memberListFragment, bundle)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.party_list_layout, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.partyName.text = data[position]
        holder.partyImage.setImageResource(when(data[position]){
            "kd" -> R.drawable.kd
            "kesk" -> R.drawable.kesk
            "kok" -> R.drawable.kok
            "liik" -> R.drawable.liik
            "ps" -> R.drawable.ps
            "r" -> R.drawable.r
            "sd" -> R.drawable.sd
            "vas" -> R.drawable.vas
            "vihr" -> R.drawable.vihr
            else -> R.drawable.parliament_icon
        })
    }
    override fun getItemCount(): Int {
        return data.size
    }
}