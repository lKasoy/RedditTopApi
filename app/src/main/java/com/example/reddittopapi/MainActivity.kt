package com.example.reddittopapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.example.reddittopapi.ui.fragments.PublicationsFragment
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<PublicationsFragment>(R.id.container, "publicationsFragment")
                setReorderingAllowed(true)
            }
        }
    }
}