package com.codingmasters.saroksarok.presentation.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.FragmentMyBinding
import com.codingmasters.saroksarok.presentation.home.DetailActivity

class MyFragment:Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = requireNotNull(_binding) { "homefragment is null" }

    private val myViewModel:MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting()
    }

    private fun setting(){
        val myAdapter = MyAdapter(
            showDetail = {content->
                val intent=Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("isBeforeMy",true)
                intent.putExtra("data", content)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
            }
        )
        binding.rvMy.adapter=myAdapter
        myAdapter.getList(myViewModel.myList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}