package com.curious.loadstatemanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun doOnClick(view: View): Unit {
        if (view.id == R.id.activity_btn) {
            startActivity(Intent(this@MainActivity, TestActivity::class.java))
        } else if (view.id == R.id.fragment_btn) {
            startActivity(Intent(this@MainActivity, TestFragmentActivity::class.java))
        }
    }

}
