package com.codingmasters.saroksarok.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.databinding.ActivityCompleteBinding
import com.codingmasters.saroksarok.presentation.MainActivity

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

        binding.btnBox.isSelected=true

        animation()
        clickHome()
        clickMy()
        clickBack()
    }

    private fun animation(){
        binding.ivPuzzle.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvId.visibility = View.INVISIBLE
        binding.tvComplete.visibility = View.INVISIBLE
        binding.btnBox.visibility = View.INVISIBLE

        // step 2. iv_puzzle fadeIn + float
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val floatAnim = AnimationUtils.loadAnimation(this, R.anim.float_up_down)

        binding.ivPuzzle.visibility = View.VISIBLE
        binding.ivPuzzle.startAnimation(fadeIn)

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                // float 시작
                binding.ivPuzzle.startAnimation(floatAnim)

                // tv_title fadeIn
                val fadeInTitle = AnimationUtils.loadAnimation(this@CompleteActivity, R.anim.fade_in)
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvTitle.startAnimation(fadeInTitle)

                fadeInTitle.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationRepeat(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        val fadeInId = AnimationUtils.loadAnimation(this@CompleteActivity, R.anim.fade_in)
                        binding.tvId.visibility = View.VISIBLE
                        binding.tvId.startAnimation(fadeInId)

                        fadeInId.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {}
                            override fun onAnimationRepeat(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {
                                val fadeInComplete = AnimationUtils.loadAnimation(this@CompleteActivity, R.anim.fade_in)
                                binding.tvComplete.visibility = View.VISIBLE
                                binding.tvComplete.startAnimation(fadeInComplete)

                                fadeInComplete.setAnimationListener(object : Animation.AnimationListener {
                                    override fun onAnimationStart(animation: Animation?) {}
                                    override fun onAnimationRepeat(animation: Animation?) {}
                                    override fun onAnimationEnd(animation: Animation?) {
                                        val fadeInBtn = AnimationUtils.loadAnimation(this@CompleteActivity, R.anim.fade_in)
                                        binding.btnBox.visibility = View.VISIBLE
                                        binding.btnBox.startAnimation(fadeInBtn)
                                    }
                                })
                            }
                        })
                    }
                })
            }
        })

        binding.ivPuzzle.startAnimation(fadeIn)
    }

    private fun clickHome(){
        binding.btnHome.setOnClickListener{
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }
    }

    private fun clickMy(){
        binding.btnBox.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("goToMy", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
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