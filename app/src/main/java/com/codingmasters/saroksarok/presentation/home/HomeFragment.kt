package com.codingmasters.saroksarok.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.FragmentHomeBinding
import com.codingmasters.saroksarok.extension.AllState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment:Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "homefragment is null" }

    private val homeViewModel:HomeViewModel by viewModels()

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting()
    }

    private fun setting(){
        val homeAdapter = HomeAdapter(
            showDetail = {content, image ->
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("data", content)
                intent.putExtra("image", image)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
        }
        )


        lifecycleScope.launch {
            homeViewModel.allState.collect{state->
                when(state){
                    is AllState.Success->{
                        binding.rvHome.adapter=homeAdapter
                        homeAdapter.getList(state.allDto.data)
                    }
                    is AllState.Loading->{}
                    is AllState.Error->{
                        Timber.e("all state is error!!")
                    }
                }
            }
        }

        homeViewModel.getAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}