package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private  lateinit var  auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth=FirebaseAuth.getInstance()
        var registerEmail: EditText =findViewById(R.id.registerEmail)
        var registerPassword: EditText =findViewById(R.id.registerPassword)
        val btnRegister: Button =findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {

            val email:String=registerEmail.text.toString()
            val password:String=registerPassword.text.toString()
            register(email,password)
        }

    }
    private fun register(email:String, password:String){

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                task->
            if(task.isSuccessful){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
                err->Toast.makeText(this,err.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }
    fun goToLogin(view: View){

        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}