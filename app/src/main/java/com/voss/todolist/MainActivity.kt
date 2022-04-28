package com.voss.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.BuildConfig
import com.voss.todolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG)
            Timber.plant(TimberTree())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()

    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navBottom = binding.navBottom

        mNavController = navHostFragment.navController
        navBottom.setupWithNavController(mNavController)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contentMonthlyFragment -> navBottom.visibility = View.GONE
                R.id.monthFragment -> navBottom.visibility = View.GONE
                R.id.editEventFragment->navBottom.visibility = View.GONE
                R.id.updateEventFragment ->navBottom.visibility = View.GONE
                R.id.browseFragment -> navBottom.visibility = View.VISIBLE
                R.id.searchFragment->navBottom.visibility = View.VISIBLE
                R.id.homeFragment ->navBottom.visibility = View.VISIBLE
            }
        }
    }
}

