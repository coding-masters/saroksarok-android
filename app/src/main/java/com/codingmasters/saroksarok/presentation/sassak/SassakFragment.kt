package com.codingmasters.saroksarok.presentation.sassak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codingmasters.saroksarok.databinding.FragmentSassakBinding

class SassakFragment:Fragment() {
    private var _binding: FragmentSassakBinding? = null
    private val binding: FragmentSassakBinding
        get() = requireNotNull(_binding) { "homefragment is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSassakBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting()
    }

    private fun setting(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}