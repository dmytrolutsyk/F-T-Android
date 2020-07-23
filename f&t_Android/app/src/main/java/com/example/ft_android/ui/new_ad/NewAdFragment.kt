package com.example.ft_android.ui.new_ad

import ApiInterface
import PutAnnonce
import PutAnnonceResult
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ft_android.BottomNavigationActivity
import com.example.ft_android.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewAdFragment : Fragment() {
    private lateinit var NewadViewModel: AdViewModel
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var category: TextView
    private lateinit var photos: TextView
    private lateinit var type: TextView
    lateinit var putannonce_button: Button
    private var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZWIwM2IxZGIzNWJkMzcxYmM2NDQxN2IiLCJ1c2VybmFtZSI6ImJlcm5hcmQiLCJwYXNzd29yZCI6IiQyYiQwNSQ1NXk4WmNtVDJobVNpSFpuSFNxelguM2kyZTRlYjVWS3RNUHd4aWpwZlo2RjJjQXkzUTJHMiIsImlhdCI6MTU5NTQ0ODc5MCwiZXhwIjoxNTk1NTM1MTkwfQ.lhOUcbGWmzTtfq7dxTB34bgQMeKUdvxCDjqrdQrHdFM"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = resources.getStringArray(R.array.Type)
        /*val spinner = activity.findViewById(R.id.spinner).setOnClickListener(this);
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, type)
            spinner.adapter = adapter*/
        NewadViewModel =
                ViewModelProviders.of(this).get(AdViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_add, container, false)
        //val textView: TextView = root.findViewById(R.id.text_newAdd)
        //NewadViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        //this.TextView =  root!!.findViewById(R.id.textView7)
        //this.TextView.text = "salut"
        //this.token = arguments!!.getString("token").toString()


        //var title: String = "velo"



        this.putannonce_button = root!!.findViewById(R.id.button2)
        this.putannonce_button.setOnClickListener {
            this.title = root!!.findViewById(R.id.editText3)
            var title: String = this.title.text.toString()

            this.description = root!!.findViewById(R.id.editText2)
            var description: String = this.description.text.toString()
            //var description: String = "il est rouillé"

            this.category = root!!.findViewById(R.id.editText5)
            var category: String = this.category.text.toString()
            //var category: String = "sport"


            this.photos = root!!.findViewById(R.id.editText6)
            var photos: String = this.photos.text.toString()
            //var photos: String = "blablou"

            this.type = root!!.findViewById(R.id.editText4)
            var typeobj: String = this.type.text.toString()
            //var typeobj: String = "don"
            println("ici "+title)
            putannonce(title, description,
                category, photos, typeobj
            )
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun putannonce(nom_objet: String, descriptionon: String, categorie: String, photo: String, typeobj: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val putad = PutAnnonce(nom_objet , descriptionon, categorie, photo, typeobj)
        retIn.putannonce(this.token, putad).enqueue(object : Callback<PutAnnonceResult> {
            override fun onFailure(call: Call<PutAnnonceResult>, t: Throwable) {
                println(t.message)
                Toast.makeText(view!!.context, "Error: Voir les logs!!!", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<PutAnnonceResult>, response: Response<PutAnnonceResult>) {
                if (response.code() == 200) {
                    val result: PutAnnonceResult = response.body()!!
                    Toast.makeText(view!!.context, "Connexion réussie !!"+result.annonce, Toast.LENGTH_SHORT).show()
                    val intent = Intent(view!!.context, BottomNavigationActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(view!!.context, "Connexion échoué!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}
