package com.codingmasters.saroksarok.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.data.response_dto.ResponseAllDto
import com.codingmasters.saroksarok.databinding.ActivityDetailBinding
import com.codingmasters.saroksarok.extension.BuyState
import com.codingmasters.saroksarok.presentation.my.ViewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.math.BigDecimal

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel:DetailViewModel by viewModels()

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
        val content = intent.getParcelableExtra<ResponseAllDto.Data>("data")
        val image=intent.getIntExtra("image", -1)
        val isBeforeMy=intent.getBooleanExtra("isBeforeMy", false)
        Timber.d("data: $content")

        if (content != null) {
            showContent(content, image)
            binding.btnBuy.isSelected=true
            if(isBeforeMy){
                clickView(content)
            }else{
                clickBuy(content)
            }

        }

        clickBack()
    }

    private fun showContent(content: ResponseAllDto.Data, image: Int) {
        with(binding) {
            ivImage.load(content.thumnailURL) {
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
            tvId.text=getString(R.string.id, content.tokenId)
            tvName.text=content.seller
            tvPrice.text=content.price
            tvType.text=content.type
            tvDescription.text=content.description

             ivBadge.visibility=(if(content.certified) View.VISIBLE else View.GONE)
        }
    }

    private fun clickView(content: ResponseAllDto.Data){

        binding.btnBuy.setText("조회하기")
        binding.btnBuy.setOnClickListener{
            val intent=Intent(this, ViewActivity::class.java)
            intent.putExtra("uri", content.fileUrl)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
        }

    }

    private fun clickBuy(content: ResponseAllDto.Data){
        val intent=Intent(this, CompleteActivity::class.java)

        lifecycleScope.launch {
            detailViewModel.buyState.collect{state->
                when(state){
                    is BuyState.Success -> {
                        intent.putExtra("content", content)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
                        finish()
                    }
                    is BuyState.Loading->{}
                    is BuyState.Error->{}
                }
            }
        }

        with(binding.btnBuy){
            setText("구매하기")
            setOnClickListener{
                detailViewModel.buy(content.tokenId, BigDecimal(content.price))
            }
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