package com.blog.demo.component.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.blog.demo.R

class LifeFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_life_fragment)

        var fragmentView = findViewById<View?>(R.id.fragment_right)
        if (fragmentView != null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.fragment_right, FragmentRight())
            ft.commitAllowingStateLoss()
        }
    }

}