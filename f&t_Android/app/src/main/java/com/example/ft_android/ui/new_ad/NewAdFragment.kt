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
import com.example.ft_android.MainActivity
import com.example.ft_android.BottomNavigationActivity
import com.example.ft_android.R
import kotlinx.android.synthetic.main.fragment_new_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class categorie (val categorie: String)

class NewAdFragment : Fragment() {
    private lateinit var NewadViewModel: AdViewModel
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var category: TextView
    private lateinit var photos: TextView
    private lateinit var type: TextView
    private lateinit var spinner: Spinner
    lateinit var putannonce_button: Button
    private var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZWNjNzI1MzdiODA4MzAwMTdjY2M4OTQiLCJ1c2VybmFtZSI6ImlwYWQiLCJwYXNzd29yZCI6IiQyYiQwNSQ4emFKUTJDbzFtNFRoR3MwZDRxTWN1YnI2bk9zYkpzYXk4QXFmZUllS1FNSGdMNjhHTHlZaSIsImlhdCI6MTU5ODg4NTAzMCwiZXhwIjoxNTk4OTcxNDMwfQ.0SqUHxgO-UwMMV02ploaJpRXKb1i_B7oO0xPYFXZAfE"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        NewadViewModel =
                ViewModelProviders.of(this).get(AdViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_add, container, false)
        //this.token = arguments!!.getString("token").toString()

        val typeofobject = resources.getStringArray(R.array.Type)
        val typeofad = resources.getStringArray(R.array.type_annonce)
        val spinner: Spinner = root.findViewById(R.id.spinner)
        val spinner2: Spinner = root.findViewById(R.id.spinner2)
        activity?.getBaseContext()?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.Type,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

        activity?.getBaseContext()?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.type_annonce,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner2.adapter = adapter
            }
        }




        this.putannonce_button = root!!.findViewById(R.id.button2)
        this.putannonce_button.setOnClickListener {
            this.title = root!!.findViewById(R.id.title)
            var title: String = this.title.text.toString()

            this.description = root!!.findViewById(R.id.description)
            var description: String = this.description.text.toString()
            //var description: String = "il est rouillé"

            //this.category = root!!.findViewById(R.id.category)
            //var category: String = this.category.text.toString()
            val category = spinner.getSelectedItem().toString()
            //var category: String = "sport"


            this.photos = root!!.findViewById(R.id.photo)
            var photos: String = this.photos.text.toString()
            //var photos: String = "blablou"

            //this.type = root!!.findViewById(R.id.type)
            //var typeobj: String = this.type.text.toString()
            val typeobj = spinner2.getSelectedItem().toString()
            //var typeobj: String = "don"
            var mainA = MainActivity()

            println("Lites déroualtes: type : "+typeobj+" categorie : "+category)

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
                    Toast.makeText(view!!.context, "Annonce publiée", Toast.LENGTH_SHORT).show()
                    val intent = Intent(view!!.context, BottomNavigationActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(view!!.context, "Connexion échoué!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}
