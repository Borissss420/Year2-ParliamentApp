package com.example.parliamentprojectwithfragments.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.parliamentprojectwithfragments.R
import com.example.parliamentprojectwithfragments.databinding.FragmentMemberDetailBinding
import com.example.parliamentprojectwithfragments.network.ParliamentProperty
import com.example.parliamentprojectwithfragments.viewModels.OverviewViewModel

class MemberDetailFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding lifecycle and viewModel
        val binding = DataBindingUtil.inflate<FragmentMemberDetailBinding>(inflater, R.layout.fragment_member_detail, container, false)
        binding.lifecycleOwner = this
        binding.memberDetailViewModel = viewModel

        //getting argument passed by previous fragment (in adapter of memberList)
        val memberArg = arguments?.get("memberKey")

        //getting details of the particular member
        viewModel.getMemberDetails(memberArg.toString())

        //Observe and update the textViews
        val observer = Observer<ParliamentProperty> {
            val uri = ("https://avoindata.eduskunta.fi/" + it.picture).toUri().buildUpon().scheme("https").build()
            Glide.with(binding.memberPictureImgV.context).load(uri).into(binding.memberPictureImgV)
            binding.memberNameTxtV.text = "My name is ${memberArg.toString()}"
            binding.memberNumbers.text = "My person number is [${it.personNumber}] and my seat number is [${it.seatNumber}]"
            val role: String = if (it.minister) "Minster" else "Member"
            binding.memberRole.text = "I am ${role} of ${it.party} from ${it.constituency} constituency"
            binding.memberAge.text = "I am ${2021 - it.bornYear} years old"

            //twitter hyperlink
            if (it.twitter == "") {
                binding.memberTwitter.text = "Twitter: N/A"
                binding.memberTwitter.setOnClickListener {
                    Toast.makeText(activity, "Not available", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                binding.memberTwitter.text = it.twitter
                binding.memberTwitter.setTextColor(Color.BLUE)
                val twitterLink = it.twitter
                binding.memberTwitter.setOnClickListener {
                    val twitterIntent = Intent(Intent.ACTION_VIEW, Uri.parse(twitterLink))
                    startActivity(twitterIntent)
                }
            }
        }
        viewModel.memberDetails.observe(viewLifecycleOwner, observer)

        //navigate to comment fragment
        binding.commentBtn.setOnClickListener {
            val bundle = bundleOf("nameKey" to memberArg, "numberKey" to viewModel.memberDetails.value?.personNumber)
            view?.findNavController()?.navigate(R.id.action_memberDetailFragment_to_commentFragment, bundle)
        }
        return binding.root
    }
}