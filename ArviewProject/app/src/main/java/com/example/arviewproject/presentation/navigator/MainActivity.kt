package com.example.arviewproject.presentation.navigator

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.arviewproject.R
import com.example.arviewproject.presentation.fragment.FirstFragment
import java.io.IOException

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ArviewProject)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showStartFragment()

    }

    private fun showStartFragment(){
        val startFragment:Fragment = FirstFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentLayout, startFragment)
            .show(startFragment)
            .commit()
    }

    override fun showNewScreen(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, f)
            .addToBackStack("TAG")
            .show(f)
            .commit()
    }

}