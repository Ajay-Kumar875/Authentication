package com.example.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth=FirebaseAuth.getInstance()

        if(auth.currentUser==null){
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(R.layout.activity_main)
        }

    }
    fun signOut(view: View){

        auth.signOut()

        val intent=Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}