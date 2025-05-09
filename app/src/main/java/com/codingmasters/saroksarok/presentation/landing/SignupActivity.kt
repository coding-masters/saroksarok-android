package com.codingmasters.saroksarok.presentation.landing

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
import com.codingmasters.saroksarok.databinding.ActivitySignupBinding
import com.codingmasters.saroksarok.presentation.MainActivity
import timber.log.Timber

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var pdfLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds() {
        binding = ActivitySignupBinding.inflate(layoutInflater)
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
                                etWallet.id,
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

                        constraintSet.applyTo(binding.clMinting)
                        checkFormComplete()
                    }
                }
            }

        binding.btnMinting.setOnClickListener {
            if (binding.btnMinting.isSelected) {
                val name = binding.etName.text.toString()
                Timber.d("name: $name")

                val intent = Intent(this, SignupCompleteActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
                finish()
            }
        }

        setAutoFocus()

        clickUpload()
        setFormListeners()
        clickBack()
    }

    private fun setAutoFocus() {
        binding.etYear.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 4) {
                    binding.etMonth.requestFocus()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.etMonth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 2) {
                    binding.etDay.requestFocus()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
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
        val titleFilled = binding.etName.text?.isNotBlank() == true
        val yearFilled = binding.etYear.text?.isNotBlank() == true
        val monthFilled = binding.etMonth.text?.isNotBlank() == true
        val dayFilled = binding.etDay.text?.isNotBlank() == true
        val priceFilled = binding.etWallet.text?.isNotBlank() == true
        val imageSelected = binding.ivImage.visibility == View.VISIBLE
        val fileSelected = binding.tvFileName.visibility == View.VISIBLE

        binding.btnMinting.isSelected =
            titleFilled && yearFilled && monthFilled && dayFilled && priceFilled && (imageSelected || fileSelected)
    }

    private fun setFormListeners() {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFormComplete()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etName.addTextChangedListener(watcher)
        binding.etYear.addTextChangedListener(watcher)
        binding.etMonth.addTextChangedListener(watcher)
        binding.etDay.addTextChangedListener(watcher)
        binding.etWallet.addTextChangedListener(watcher)
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