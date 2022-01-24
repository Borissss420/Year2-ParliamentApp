package com.example.parliamentprojectwithfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.adapters.PartyListAdapter
import com.example.parliamentprojectwithfragments.databinding.FragmentPartyListBinding
import com.example.parliamentprojectwithfragments.viewModels.OverviewViewModel

class PartyListFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding lifecycle and viewModel
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(inflater,
            R.layout.fragment_party_list, container, false)
        binding.lifecycleOwner = this
        binding.partyListViewModel = viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recyclerview
        val partyList = view.findViewById<RecyclerView>(R.id.partyList)
        val viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)

        val observer = Observer<Set<String>> {
            partyList.layoutManager = LinearLayoutManager(activity)
            partyList.adapter = PartyListAdapter(viewModel.party.value!!.toList())
        }
        viewModel.party.observe(viewLifecycleOwner, observer)
    }
}