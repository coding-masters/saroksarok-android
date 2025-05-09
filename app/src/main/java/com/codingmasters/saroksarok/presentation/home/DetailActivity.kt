package com.codingmasters.saroksarok.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.Content
import com.codingmasters.saroksarok.databinding.ActivityDetailBinding
import timber.log.Timber

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting() {
        val content = intent.getParcelableExtra<Content>("data")
        Timber.d("data: $content")

        if (content != null) {
            showContent(content)
            clickBuy(content)
        }

        clickBack()
    }

    private fun showContent(content: Content) {
        with(binding) {
            ivImage.load(content.image) {
                transformations(
                    RoundedCornersTransformation(
                        topLeft = 30f,
                        topRight = 30f,
                        bottomLeft = 30f,
                        bottomRight = 30f
                    )
                )
            }

            ivProfile.load(R.drawable.ic_profile_black){
                transformations(CircleCropTransformation())
            }

            tvTitle.text=content.title
            tvId.text=content.id
            tvName.text=content.seller
            tvPrice.text=content.price
            tvType.text=content.type
            tvDescription.text=content.description

             ivBadge.visibility=(if(content.certified) View.VISIBLE else View.GONE)
        }
    }

    private fun clickBuy(content: Content){
        binding.btnBuy.setOnClickListener{
            val intent=Intent(this, CompleteActivity::class.java)
            intent.putExtra("content", content)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
            finish()
        }
    }

    private fun clickBack() {
        binding.btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }
    }
}