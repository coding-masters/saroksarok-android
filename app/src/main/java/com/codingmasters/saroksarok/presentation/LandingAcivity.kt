package com.codingmasters.saroksarok.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityLandingBinding

class LandingAcivity:AppCompatActivity() {
    private lateinit var binding:ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding=ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting(){
        val intent=Intent(this, SignupActivity::class.java)
        with(binding){
            btnExpert.isSelected=true
            btnNormal.isSelected=true

            btnExpert.setOnClickListener{
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
            }
        }
    }
}