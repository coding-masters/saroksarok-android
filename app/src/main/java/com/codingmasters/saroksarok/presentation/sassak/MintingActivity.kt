package com.codingmasters.saroksarok.presentation.sassak

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityMintingBinding

class MintingActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMintingBinding

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds(){
        binding=ActivityMintingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting(){
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    with(binding){
                        ivImage.visibility=View.VISIBLE
                        binding.ivImage.setImageURI(it)

                        val constraintSet = ConstraintSet()
                        constraintSet.clone(binding.clMinting) // root는 ConstraintLayout의 ID

                        constraintSet.connect(
                            btnUpload.id,
                            ConstraintSet.TOP,
                            ivImage.id,
                            ConstraintSet.BOTTOM,
                            16.dpToPx() // 마진
                        )

                        constraintSet.connect(
                            etPrice.id,
                            ConstraintSet.BOTTOM,
                            btnMinting.id,
                            ConstraintSet.TOP,
                            80.dpToPx()
                        )

                        constraintSet.applyTo(binding.clMinting)
                        checkFormComplete()
                    }
                }
            }
        }

        clickUpload()
        setFormListeners()
        clickBack()
    }

    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun clickUpload(){
        binding.btnUpload.setOnClickListener{
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        galleryLauncher.launch(intent)
    }

    private fun checkFormComplete() {
        val titleFilled = binding.etTitle.text?.isNotBlank() == true
        val descriptionFilled = binding.etDescription.text?.isNotBlank() == true
        val priceFilled = binding.etPrice.text?.isNotBlank() == true
        val imageSelected = binding.ivImage.visibility == View.VISIBLE

        with(binding.btnMinting){
            isSelected = titleFilled && descriptionFilled && priceFilled && imageSelected
            if(isSelected){
                setOnClickListener{
                    finish()
                    overridePendingTransition(R.anim.stay, R.anim.slide_out_left)
                }
            }
        }
    }

    private fun setFormListeners() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFormComplete()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etTitle.addTextChangedListener(watcher)
        binding.etDescription.addTextChangedListener(watcher)
        binding.etPrice.addTextChangedListener(watcher)
    }

    private fun clickBack() {
        binding.btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_left)
        }

        onBackPressedDispatcher.addCallback(this) {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.slide_out_left)
        }
    }
}