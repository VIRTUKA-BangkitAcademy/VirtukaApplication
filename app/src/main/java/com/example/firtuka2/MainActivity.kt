package com.example.firtuka2

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firtuka2.databinding.ActivityMainBinding
import com.example.firtuka2.ui.camera.CameraARActivity
import com.example.firtuka2.ui.camera.CameraFragment
import com.example.firtuka2.ui.home.HomeFragment
import com.example.firtuka2.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        replaceFragment(HomeFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_camera -> {
//                        val intent = Intent(this, CameraARActivity::class.java)
//                        startActivity(intent)
                        replaceFragment(CameraFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_profile -> {
                        replaceFragment(ProfileFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        binding.navView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.commit()
    }
}