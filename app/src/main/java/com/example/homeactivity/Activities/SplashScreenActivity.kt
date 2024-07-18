package com.example.homeactivity.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreenDuration: Long = 1000 // 1 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Hide the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                // User is signed in, navigate to MainActivity
                val mainIntent = Intent(this, MainActivity::class.java)
                mainIntent.putExtra("user_email", currentUser.email)
                startActivity(mainIntent)
                finish()
            } else {
                // No user is signed in, navigate to IntroductionActivity
                val introIntent = Intent(this, IntroductionActivity::class.java)
                startActivity(introIntent)
                finish()
            }
        }, splashScreenDuration)

    }
}