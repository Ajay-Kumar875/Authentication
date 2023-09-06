package com.example.authentication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private  lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth=FirebaseAuth.getInstance()

        var loginEmail:EditText=findViewById(R.id.loginEmail)
        var loginPassword:EditText=findViewById(R.id.loginPassword)
        val btnLogin:Button=findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val email:String=loginEmail.text.toString()
            val password:String=loginPassword.text.toString()

            if(email==="" || password==="" || password.length<8) {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
            login(email,password)
        }
    }

    private fun login(email:String, password:String){

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                task->
            if(task.isSuccessful){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
                err-> Toast.makeText(this,err.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
    fun goToRegister(view: View){

        val intent=Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}