package com.example.homeactivity.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {
   private lateinit var binding :ActivityIntroductionBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

         // Hide the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()



        binding.started.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}