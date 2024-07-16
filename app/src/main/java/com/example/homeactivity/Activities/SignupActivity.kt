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
import com.example.homeactivity.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modifyStatusBar()
        //firebase
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signupButton.setOnClickListener{
            val emailText = binding.emailText.text.toString()
            val pswdText = binding.pswdText.text.toString()
            val retypePswdText = binding.retypePswdText.text.toString()
            if(emailText.isNotEmpty()&& pswdText.isNotEmpty()&&retypePswdText.isNotEmpty()){
                if(pswdText == retypePswdText){
                    firebaseAuth.createUserWithEmailAndPassword(emailText,pswdText).addOnCompleteListener{
                        if(it.isSuccessful){
                            firebaseAuth.signOut() // Sign out the user
                            Toast.makeText(this, "User is created!! Directing to login page", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password is not matching !!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty Fields!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginTextButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
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