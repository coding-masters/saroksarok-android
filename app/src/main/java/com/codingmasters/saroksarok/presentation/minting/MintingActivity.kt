package com.codingmasters.saroksarok.presentation.minting

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
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

class MintingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMintingBinding

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var pdfLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds() {
        binding = ActivityMintingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting() {
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val uri = result.data?.data
                    uri?.let {
                        with(binding) {
                            ivImage.visibility = View.VISIBLE
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

        // PDF 런처
        pdfLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val uri = result.data?.data
                    uri?.let {
                        val fileName = getFileNameFromUri(it)
                        binding.tvFileName.apply {
                            visibility = View.VISIBLE
                            text = fileName ?: "선택한 파일 이름 없음"
                        }

                        val constraintSet = ConstraintSet()
                        constraintSet.clone(binding.clMinting) // root는 ConstraintLayout의 ID

                        constraintSet.connect(
                            binding.btnUpload.id,
                            ConstraintSet.TOP,
                            binding.tvFileName.id,
                            ConstraintSet.BOTTOM,
                            16.dpToPx() // 마진
                        )

                        constraintSet.connect(
                            binding.etPrice.id,
                            ConstraintSet.BOTTOM,
                            binding.btnMinting.id,
                            ConstraintSet.TOP,
                            80.dpToPx()
                        )

                        constraintSet.applyTo(binding.clMinting)

                        checkFormComplete()
                    }
                }
            }


        binding.btnMinting.setOnClickListener {
            if (binding.btnMinting.isSelected) {
                val intent = Intent(this@MintingActivity, MintingCompleteActivity::class.java)
                intent.putExtra("title", binding.etTitle.text.toString())
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.stay, R.anim.slide_out_left)
            }

        }


        clickUpload()
        setFormListeners()
        clickBack()
    }

    private fun getFileNameFromUri(uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(nameIndex)
        }
    }


    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun clickUpload() {
        binding.btnUpload.setOnClickListener {
            val options = arrayOf("사진 선택", "PDF 파일 선택")

            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("업로드 방식 선택")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> openGallery()
                        1 -> openPdfPicker()
                    }
                }
                .show()
        }
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        galleryLauncher.launch(intent)
    }

    private fun openPdfPicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        pdfLauncher.launch(intent)
    }

    private fun checkFormComplete() {
        val titleFilled = binding.etTitle.text?.isNotBlank() == true
        val descriptionFilled = binding.etDescription.text?.isNotBlank() == true
        val priceFilled = binding.etPrice.text?.isNotBlank() == true
        val imageSelected = binding.ivImage.visibility == View.VISIBLE
        val fileSelected = binding.tvFileName.visibility == View.VISIBLE


        binding.btnMinting.isSelected =
            titleFilled && descriptionFilled && priceFilled && (imageSelected || fileSelected)
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