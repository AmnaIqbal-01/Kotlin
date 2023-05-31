package com.example.authenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.authenticationdemo.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    //Declare an instance of FirebaseAuth
    private lateinit var binding: ActivityLogInBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.signuptextview.setOnClickListener {
            val intent = Intent(this, signIn::class.java)
            startActivity(intent)
        }

        binding.LogIn.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}