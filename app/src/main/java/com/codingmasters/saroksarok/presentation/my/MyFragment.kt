package com.codingmasters.saroksarok.presentation.my

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.FragmentMyBinding
import com.codingmasters.saroksarok.extension.MyBuyState
import com.codingmasters.saroksarok.extension.MyOnSaleState
import com.codingmasters.saroksarok.presentation.home.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyFragment:Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = requireNotNull(_binding) { "homefragment is null" }

    private val myViewModel:MyViewModel by viewModels()
    private lateinit var myAdapter: MyAdapter
    private var currentTabIsRegistered = true

    companion object {
        fun newInstance(): MyFragment = MyFragment()
    }

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
        myAdapter = MyAdapter(
            showDetail = {content->
                val intent=Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra("isBeforeMy",true)
                intent.putExtra("data", content)
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
            }
        )
        binding.rvMy.adapter=myAdapter
        switchView()

        switchToTab(true)

        binding.tvRegistered.setOnClickListener {
            switchToTab(true)
        }

        binding.tvPurchased.setOnClickListener {
            switchToTab(false)
        }
    }

    private fun switchView(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    myViewModel.myOnSaleState.collect { state ->
                        when (state) {
                            is MyOnSaleState.Success -> {
                                if (currentTabIsRegistered) {
                                    myAdapter.getList(state.allDto.data)
                                }
                            }
                            is MyOnSaleState.Error -> {
                                // 에러 처리 (예: Toast)
                            }
                            is MyOnSaleState.Loading -> { /* 로딩 UI */ }
                            else->{"Error"}
                        }
                    }
                }

                launch {
                    myViewModel.myBuyState.collect { state ->
                        when (state) {
                            is MyBuyState.Success -> {
                                if (!currentTabIsRegistered) {
                                    myAdapter.getList(state.allDto.data)
                                }
                            }
                            is MyBuyState.Error -> {
                                // 에러 처리
                            }
                            is MyBuyState.Loading -> { /* 로딩 UI */ }
                        }
                    }
                }
            }
        }
    }

    private fun switchToTab(isRegistered: Boolean) {
        currentTabIsRegistered = isRegistered

        // 텍스트 스타일
        binding.tvRegistered.setTypeface(null, if (isRegistered) Typeface.BOLD else Typeface.NORMAL)
        binding.tvPurchased.setTypeface(null, if (!isRegistered) Typeface.BOLD else Typeface.NORMAL)

        // 인디케이터 이동
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
        val params = binding.indicator.layoutParams as ConstraintLayout.LayoutParams
        if (isRegistered) {
            params.startToStart = binding.tvRegistered.id
            params.endToEnd = binding.tvRegistered.id
        } else {
            params.startToStart = binding.tvPurchased.id
            params.endToEnd = binding.tvPurchased.id
        }
        binding.indicator.layoutParams = params

        // 📌 ViewModel 데이터 요청
        if (isRegistered) {
            myViewModel.getOnSale()

            // 🔽 강제 갱신: 상태값이 이미 Success일 경우 대비
            myViewModel.myOnSaleState.value.let { state ->
                if (state is MyOnSaleState.Success) {
                    myAdapter.getList(state.allDto.data)
                }
            }
        } else {
            myViewModel.getBuy()

            // 🔽 강제 갱신
            myViewModel.myBuyState.value.let { state ->
                if (state is MyBuyState.Success) {
                    myAdapter.getList(state.allDto.data)
                }
            }
        }

    }

    /*private fun switchToTab(isRegistered: Boolean) {
        // 텍스트 스타일 변경
        binding.tvRegistered.setTypeface(null, if (isRegistered) Typeface.BOLD else Typeface.NORMAL)
        binding.tvPurchased.setTypeface(null, if (!isRegistered) Typeface.BOLD else Typeface.NORMAL)

        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)

        val params = binding.indicator.layoutParams as ConstraintLayout.LayoutParams
        if (isRegistered) {
            params.startToStart = binding.tvRegistered.id
            params.endToEnd = binding.tvRegistered.id
        } else {
            params.startToStart = binding.tvPurchased.id
            params.endToEnd = binding.tvPurchased.id
        }
        binding.indicator.layoutParams = params

        // RecyclerView 데이터 교체
        myAdapter.getList(if (isRegistered) myViewModel.registeredList else myViewModel.purchasedList)
    }*/

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}