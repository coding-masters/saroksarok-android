package com.codingmasters.saroksarok.presentation.my

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityViewBinding

class ViewActivity:AppCompatActivity() {
    private lateinit var binding:ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding=ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting(){
        val webView = binding.wvView // 또는 findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        val pdfUrl = intent.getStringExtra("uri")
        val encodedUrl = Uri.encode(pdfUrl)
        val googleDocsUrl = "https://docs.google.com/gview?embedded=true&url=$encodedUrl"

        webView.loadUrl(googleDocsUrl)

        clickBack()
    }

    private fun clickBack() {
        binding.btnClose.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_right)
        }
    }
}