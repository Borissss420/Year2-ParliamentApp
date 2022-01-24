package com.example.parliamentprojectwithfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main, container, false)

        //Navigate to partyList fragment
        binding.partyBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_partyListFragment)
        }
        //Navigate to search fragment
        binding.searchBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
        return binding.root
    }
}