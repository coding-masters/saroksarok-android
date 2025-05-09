package com.codingmasters.saroksarok.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityMainBinding
import com.codingmasters.saroksarok.presentation.home.HomeFragment
import com.codingmasters.saroksarok.presentation.minting.MintingActivity
import com.codingmasters.saroksarok.presentation.my.MyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastBackPressedTime = 0L
    private var currentFragmentTag: String? = null

    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinds()
        setting()
    }

    private fun initBinds() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setting() {
        val name=intent.getStringExtra("name")
        if (name != null) {
            mainViewModel.saveName(name)
        }
            setupBottomNavigation(mainViewModel.getName())
    }

    private fun setupBottomNavigation(name:String) {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    openFragmentOncePerTab("HOME") { HomeFragment.newInstance() }
                    true
                }
                R.id.menu_my -> {
                    openFragmentOncePerTab("MY") { MyFragment.newInstance() }
                    true
                }
                R.id.menu_minting -> {
                    val intent = Intent(this, MintingActivity::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
                    false
                }
                else -> false
            }
        }

        // 기본 탭 설정
        binding.bnvMain.selectedItemId = R.id.menu_home
    }

    private fun openFragmentOncePerTab(tag: String, fragmentSupplier: () -> Fragment) {
        val fragmentManager = supportFragmentManager

        val existingFragment = fragmentManager.findFragmentByTag(tag)

        // 현재 보여지는 Fragment와 같으면 새로 생성
        if (currentFragmentTag == tag && existingFragment != null) {
            fragmentManager.beginTransaction()
                .remove(existingFragment)
                .commit()
        }

        val newFragment = fragmentSupplier()
        fragmentManager.beginTransaction()
            .replace(R.id.fcv_main, newFragment, tag)
            .commit()

        currentFragmentTag = tag
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .addToBackStack(null) // 뒤로가기 가능
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val now = System.currentTimeMillis()
            if (now - lastBackPressedTime < 2000) {
                finish()
            } else {
                Toast.makeText(this, "한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
                lastBackPressedTime = now
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)  // 항상 호출해야 함

        intent?.let {
            if (it.getBooleanExtra("goToMy", false)) {
                binding.bnvMain.selectedItemId = R.id.menu_my
            }
        }
    }


}