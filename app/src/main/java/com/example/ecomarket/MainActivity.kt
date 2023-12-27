package com.example.ecomarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ecomarket.databinding.ActivityMainBinding
import com.example.ui.extensions.hide
import com.example.ui.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.example.main.R.id.mainFragment,
                com.example.basket.R.id.basketFragment,
                com.example.story.R.id.storyFragment,
                com.example.info.R.id.infoFragment -> {
                    binding.navView.show()
                }
                else -> {
                    lifecycleScope.launch { binding.navView.hide() }
                }
            }
        }
    }
}