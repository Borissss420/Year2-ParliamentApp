package com.example.parliamentprojectwithfragments.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.adapters.SearchMemberListAdapter
import com.example.parliamentprojectwithfragments.databinding.FragmentSearchBinding
import com.example.parliamentprojectwithfragments.viewModels.SearchMemberViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchMemberViewModel by lazy {
        ViewModelProvider(this).get(SearchMemberViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        //binding lifecycle and viewModel
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = this
        binding.searchMemberViewModel = viewModel

        //Observe the memberList from viewModel and update the Recyclerview
        viewModel.memberList.observe(viewLifecycleOwner, {
            binding.searchMemberList.layoutManager = LinearLayoutManager(activity)
            binding.searchMemberList.adapter = SearchMemberListAdapter(it)
        })

        //EditText search bar
        binding.searchET.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                //Filtering in viewModel
                viewModel.filter(s.toString())
                //Observe the filtered list from viewModel and update the Recyclerview
                viewModel.filterList.observe(viewLifecycleOwner, {
                    binding.searchMemberList.layoutManager = LinearLayoutManager(activity)
                    binding.searchMemberList.adapter = SearchMemberListAdapter(it)
                })
            }
        })
        return binding.root
    }
}