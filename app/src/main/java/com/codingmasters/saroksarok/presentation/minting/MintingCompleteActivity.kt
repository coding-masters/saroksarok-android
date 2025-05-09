package com.codingmasters.saroksarok.presentation.minting

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityMintingCompleteBinding
import com.codingmasters.saroksarok.presentation.MainActivity

class MintingCompleteActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMintingCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding=ActivityMintingCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting(){
        binding.btnMy.isSelected=true

        val title=intent.getStringExtra("title")
        binding.tvTitle.text=title

        animation()
        clickMy()
    }

    private fun animation(){
        binding.ivComplete.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvComplete.visibility = View.INVISIBLE
        binding.btnMy.visibility = View.INVISIBLE

        val floatAnim = AnimationUtils.loadAnimation(this, R.anim.float_up_down)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        binding.ivComplete.visibility = View.VISIBLE
        binding.ivComplete.startAnimation(fadeIn)

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // 둥실 애니메이션 시작
                binding.ivComplete.startAnimation(floatAnim)

                // tvTitle fadeIn
                val fadeInTitle = AnimationUtils.loadAnimation(this@MintingCompleteActivity, R.anim.fade_in)
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvTitle.startAnimation(fadeInTitle)

                fadeInTitle.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationRepeat(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        val fadeInComplete = AnimationUtils.loadAnimation(this@MintingCompleteActivity, R.anim.fade_in)
                        binding.tvComplete.visibility = View.VISIBLE
                        binding.tvComplete.startAnimation(fadeInComplete)

                        fadeInComplete.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {}
                            override fun onAnimationRepeat(animation: Animation?) {}

                            override fun onAnimationEnd(animation: Animation?) {
                                val fadeInBtn = AnimationUtils.loadAnimation(this@MintingCompleteActivity, R.anim.fade_in)
                                binding.btnMy.visibility = View.VISIBLE
                                binding.btnMy.startAnimation(fadeInBtn)
                            }
                        })
                    }
                })
            }
        })

        binding.ivComplete.startAnimation(fadeIn)
    }

    private fun clickMy(){
        binding.btnMy.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("goToMy", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_left)
        }
    }
}