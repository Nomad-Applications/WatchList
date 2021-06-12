package com.nomadapps.watchlist.view.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nomadapps.watchlist.view.R
import kotlinx.android.synthetic.main.activity_sign_in.button
import kotlinx.android.synthetic.main.activity_sign_in.editTextEmail
import kotlinx.android.synthetic.main.activity_sign_in.editTextPassword
import kotlinx.android.synthetic.main.activity_sign_in.textView3
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var pass : String
    lateinit var email : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = Firebase.auth
        button.setOnClickListener {
            email = editTextEmail.text.toString()
            pass = editTextPassword.text.toString()
            if(email.isNotEmpty() || pass.isNotEmpty()){
                signIn(email, pass)
            }else{
                Toast.makeText(baseContext, "Email or Password cannot be empty",
                    Toast.LENGTH_SHORT).show()
            }

        }
        textView3.setOnClickListener {
            val i = Intent(this, SignUpActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "Sign In Succesfully",
                        Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MovieActivity::class.java)
                    startActivity(i)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Please Try Again",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }
}