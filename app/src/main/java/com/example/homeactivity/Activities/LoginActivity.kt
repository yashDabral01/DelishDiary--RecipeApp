package com.example.homeactivity.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modifyStatusBar()
        //Firebase initialization
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener{
            val emailText = binding.emailText.text.toString()
            val pswdText = binding.pswdText.text.toString()

            if(emailText.isNotEmpty()&& pswdText.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(emailText,pswdText).addOnCompleteListener{
                        if(it.isSuccessful){

                            Toast.makeText(this, "Logged in successfully!!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("user_email",emailText)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Empty Fields!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signupTextButton.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun modifyStatusBar() {
        // Change status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        // Change status bar icon colors to grey
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}