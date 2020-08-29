package com.example.ft_android.ui.account

import ApiInterface
import PatchUser
import PatchUserResult
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ft_android.BottomNavigationActivity
import com.example.ft_android.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var usernameField: TextView
    private lateinit var passwordField: TextView
    private lateinit var emailField: TextView
    private lateinit var typeUserField: TextView
    private lateinit var phoneField: TextView
    private lateinit var cityField: TextView
    lateinit var button: Button
    private var token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZWIwM2IxZGIzNWJkMzcxYmM2NDQxN2IiLCJ1c2VybmFtZSI6ImJlcm5hcmQiLCJwYXNzd29yZCI6IiQyYiQwNSQ1NXk4WmNtVDJobVNpSFpuSFNxelguM2kyZTRlYjVWS3RNUHd4aWpwZlo2RjJjQXkzUTJHMiIsImlhdCI6MTU5ODcxMTIyOSwiZXhwIjoxNTk4Nzk3NjI5fQ.G-nsNOvthr-luLwGaGHF9oEPXFdCcvLBCGBKDU8DA-8"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        // val textView: TextView = root.findViewById(R.id.text_account)
        //accountViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        this.button = root!!.findViewById(R.id.button)
        this.button.setOnClickListener {
            this.usernameField = root!!.findViewById(R.id.usernameField)
            var usernameField: String = this.usernameField.text.toString()

            this.passwordField = root!!.findViewById(R.id.passwordField)
            var passwordField: String = this.passwordField.text.toString()


            this.emailField = root!!.findViewById(R.id.emailField)
            var emailField: String = this.emailField.text.toString()


            this.typeUserField = root!!.findViewById(R.id.typeUserField)
            var typeUserField: String = this.typeUserField.text.toString()


            this.phoneField = root!!.findViewById(R.id.phoneField)
            var phoneField: String = this.phoneField.text.toString()


            this.cityField = root!!.findViewById(R.id.cityField)
            var cityField: String = this.cityField.text.toString()


            println("ici " + usernameField)
            patchuser(
                usernameField, passwordField,
                cityField, emailField, typeUserField, phoneField
            )
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun patchuser(username: String, password: String, ville: String, email: String, status_user: String, phone: String) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val patchuser = PatchUser(username, password, ville, email, status_user, phone)
        retIn.patchuser(this.token, patchuser).enqueue(object : Callback<PatchUserResult> {
            override fun onFailure(call: Call<PatchUserResult>, t: Throwable) {
                println(t.message)
                Toast.makeText(view!!.context, "Error: Voir les logs!!!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<PatchUserResult>,
                response: Response<PatchUserResult>
            ) {
                if (response.code() == 200) {
                    val result: PatchUserResult = response.body()!!
                    Toast.makeText(
                        view!!.context,
                        "Connexion réussie !!" + result.user,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(view!!.context, BottomNavigationActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(view!!.context, "Connexion échoué!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
