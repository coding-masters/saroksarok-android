package com.codingmasters.saroksarok.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.codingmasters.saroksarok.R
import com.codingmasters.saroksarok.databinding.ActivityMainBinding
import com.codingmasters.saroksarok.presentation.sassak.MintingActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentTabId = R.id.menu_home
    private var lastBackPressedTime = 0L

    private lateinit var navHostMap: Map<Int, FragmentContainerView>

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
        clickBnv()
    }

    private fun clickBnv(){
        binding.bnvMain.selectedItemId = R.id.menu_home
        navHostMap = mapOf(
            R.id.menu_home to binding.fcvHome,
            R.id.menu_my to binding.fcvMy,
        )

        binding.bnvMain.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.menu_minting) {
                startActivity(Intent(this, MintingActivity::class.java))
                overridePendingTransition(R.anim.slide_in_left, R.anim.stay)
                false // false 반환 → 선택된 상태 유지 X
            } else {
                switchTab(item.itemId)
                true
            }
        }
    }

    private fun switchTab(targetTabId: Int) {
        val isReselected = targetTabId == currentTabId

        navHostMap.forEach { (id, container) ->
            container.visibility = if (id == targetTabId) View.VISIBLE else View.GONE
        }

        val navHostFragment = supportFragmentManager.findFragmentById(
            navHostMap[targetTabId]!!.id
        ) as NavHostFragment

        val navController = navHostFragment.navController

        if (isReselected) {
            // 1. 루트가 아닐 경우 popBackStack
            val popped = navController.popBackStack(
                navController.graph.startDestinationId,
                false
            )
            // 2. 이미 루트 상태였다면 → onTabReselected() 호출
            if (!popped) {
                val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull()
                if (currentFragment is OnTabReselectedListener) {
                    currentFragment.onTabReselected()
                }
            }
        }

        currentTabId = targetTabId
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(navHostMap[currentTabId]!!.id) as NavHostFragment
        val navController = navHostFragment.navController

        // 현재 탭에서 popBackStack 할 게 있다면 pop
        if (!navController.popBackStack()) {
            // pop할 게 없으면 → 종료 로직
            val now = System.currentTimeMillis()
            if (now - lastBackPressedTime < 2000) {
                finish()
            } else {
                Toast.makeText(this, "한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
                lastBackPressedTime = now
            }
        }
    }

}