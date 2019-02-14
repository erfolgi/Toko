package com.erfolgi.toko.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.erfolgi.toko.R

class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        handler= Handler()
        handler.postDelayed({
            val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)

    }
}
