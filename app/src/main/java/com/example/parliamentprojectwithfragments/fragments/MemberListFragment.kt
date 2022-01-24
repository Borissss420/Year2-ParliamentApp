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
import com.example.parliamentprojectwithfragments.adapters.MemberListAdapter
import com.example.parliamentprojectwithfragments.databinding.FragmentMemberListBinding
import com.example.parliamentprojectwithfragments.network.ParliamentProperty
import com.example.parliamentprojectwithfragments.viewModels.OverviewViewModel

class MemberListFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding lifecycle and viewModel
        val binding = DataBindingUtil.inflate<FragmentMemberListBinding>(inflater, R.layout.fragment_member_list, container, false)
        binding.lifecycleOwner = this
        binding.memberListViewModel = viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recyclerview
        val memberList = view.findViewById<RecyclerView>(R.id.memberList)
        val viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        val partyName = arguments?.get("partyNameKey")

        viewModel.getMembersInParty(partyName.toString())

        val observer = Observer<List<ParliamentProperty>> {
            memberList.layoutManager = LinearLayoutManager(activity)
            memberList.adapter = MemberListAdapter(viewModel.memberOfParty.value!!)
        }
        viewModel.memberOfParty.observe(viewLifecycleOwner, observer)
    }
}