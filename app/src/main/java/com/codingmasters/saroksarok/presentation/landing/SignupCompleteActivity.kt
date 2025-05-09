package com.codingmasters.saroksarok.presentation.landing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivitySignupCompleteBinding
import com.codingmasters.saroksarok.presentation.MainActivity
import timber.log.Timber

class SignupCompleteActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySignupCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding= ActivitySignupCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting() {
        val name = intent.getStringExtra("name") ?: "사용자"
        Timber.d("complete name: $name")
        binding.tvName.text = getString(R.string.signup_name, name)

        // 모든 뷰 미리 INVISIBLE로 설정
        binding.ivComplete.visibility = View.INVISIBLE
        binding.tvName.visibility = View.INVISIBLE
        binding.tvComplete.visibility = View.INVISIBLE

        val fadeInIv = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeInName = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeInComplete = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // STEP 1: ivComplete 애니메이션 리스너 등록
        fadeInIv.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivComplete.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                // 둥실둥실 애니메이션
                val floatAnim = AnimationUtils.loadAnimation(this@SignupCompleteActivity, R.anim.float_up_down)
                binding.ivComplete.startAnimation(floatAnim)

                // STEP 2: tvName 애니메이션
                fadeInName.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        binding.tvName.visibility = View.VISIBLE
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        // STEP 3: tvComplete 애니메이션
                        fadeInComplete.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {
                                binding.tvComplete.visibility = View.VISIBLE
                            }

                            override fun onAnimationEnd(animation: Animation?) {
                                // STEP 4: 2초 후 Main 이동
                                Handler(Looper.getMainLooper()).postDelayed({
                                    val intent=Intent(this@SignupCompleteActivity, MainActivity::class.java)
                                    intent.putExtra("name", name)
                                    startActivity(intent)
                                    finish()
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
                                }, 2000)
                            }

                            override fun onAnimationRepeat(animation: Animation?) {}
                        })

                        binding.tvComplete.startAnimation(fadeInComplete)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })

                binding.tvName.startAnimation(fadeInName)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        // 애니메이션 시작은 가장 마지막에
        binding.ivComplete.startAnimation(fadeInIv)
    }

}