package com.example.ft_android

import ApiInterface
import SignInBody
import SignInResult
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ft_android.ui.home.HomeFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var ValidateBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ValidateBtn = findViewById<View>(R.id.ValidateBtn)
        ValidateBtn.setOnClickListener {
            //Navigate
            var Username = findViewById<View>(R.id.UsernameField) as EditText
            val Usernamecpt = Username.text.toString()
            var Password = findViewById<View>(R.id.PasswordField) as EditText
            val Passwordcpt = Password.text.toString()

            //val intent = Intent(this, Homepage::class.java)
            val intent = Intent(this, BottomNavigationActivity::class.java)
            intent.putExtra("Username", Usernamecpt)
            intent.putExtra("Password", Passwordcpt)

            val text = "Veuillez renseigner tous les champs"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            if (Usernamecpt == "" || Passwordcpt == ""){
                toast.show()
            }
            else {
                signin(Usernamecpt, Passwordcpt)
            }
        }
    }
    private fun signin(username: String, password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signIn = SignInBody(username , password)
        retIn.signin(signIn).enqueue(object : Callback<SignInResult> {
            override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                println(t.message)
                Toast.makeText(applicationContext, "Error: Voir les logs!!!", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<SignInResult>, response: Response<SignInResult>) {
                if (response.code() == 200) {
                    val result: SignInResult = response.body()!!
                    Toast.makeText(applicationContext, "Connexion réussie !!"+result.token, Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, BottomNavigationActivity::class.java)
                    intent.putExtra("token", result.token)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Connexion échoué!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}

