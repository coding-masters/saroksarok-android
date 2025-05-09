package com.codingmasters.saroksarok.presentation.home

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.databinding.ActivityCompleteBinding

class CompleteActivity:AppCompatActivity() {
    private lateinit var binding:ActivityCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding=ActivityCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting(){
        val content=intent.getParcelableExtra<Content>("content")
        if(content!=null){
            with(binding){
                tvTitle.text=content.title
                tvId.text=content.id
            }
        }

        val animation = AnimationUtils.loadAnimation(this, R.anim.float_up_down)
        binding.ivPuzzle.startAnimation(animation)

        binding.btnBox.isSelected=true
        clickHome()
        clickBack()
    }

    private fun clickHome(){
        binding.btnHome.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }
    }

    private fun clickBack() {
        onBackPressedDispatcher.addCallback(this) {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }
    }
}