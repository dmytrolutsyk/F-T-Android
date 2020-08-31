package com.example.ft_android.ui.home

import ApiInterface
import GetAnnoncesResult
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ft_android.Annonce
import com.example.ft_android.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class HomeFragment(
    var annoncedata: ArrayList<Annonce> = ArrayList<Annonce>()
    /*private val annonces: List<Annonces> = listOf(
        Annonces("Ballon", "Echange", "Sport","Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Roue", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ordinateur", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Pot de fleur", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Robe", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU")
    )*/
    //private val annonces: ArrayList<Annonces>? = null
    //private var annonces: List<Annonces> = emptyList(1)
    //var annoncesTest = listOf<Annonces>()
    //var myList: MutableList<Annonces> = mutableListOf<Annonces>()
) : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1ZjRhOTY1MDk5MDY0OTAwMTdjZWM4NDUiLCJ1c2VybmFtZSI6ImRteXRybyIsInBhc3N3b3JkIjoiJDJiJDA1JEZGT0RxOHJYeFg2Mk1ETmo4TmVuRE9SWlBpVm01Sk5BTlpwRjVFMmZrUHhkUUdCa1gySGRtIiwiaWF0IjoxNTk4ODE4NzExLCJleHAiOjE1OTg5MDUxMTF9.VKzxSI6kgkoZzGW5Q7F8r3_91OsZy0w6rnVF5Kr9N78"


    data class Annonces(var titre: String, var categorie: String, var type: String, var description: String, var urlImage: String)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val DeconnexionBtn = root.findViewById<Button>(R.id.DeconnexionBtn)
        DeconnexionBtn.setOnClickListener {
            //val intent = Intent(this, MainActivity::class.java)
            val root2 = inflater.inflate(R.layout.activity_main, container, false)
           // startActivity(root2)

        }*/
        //val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        getannonces()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
        }

    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private fun getannonces(){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.getannonces(this.token).enqueue(object : Callback<GetAnnoncesResult> {
            override fun onFailure(call: Call<GetAnnoncesResult>, t: Throwable) {

                Toast.makeText(view!!.context, "Error: Voir les logs!!!", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<GetAnnoncesResult>, response: Response<GetAnnoncesResult>) {
                if (response.code() == 200) {
                    val result: GetAnnoncesResult = response.body()!!
                    Toast.makeText(view!!.context, "Annonces récupérées", Toast.LENGTH_SHORT).show()

                    var annoncedata: ArrayList<Annonce> = ArrayList<Annonce>()
                    result.annonces.forEach {
                        var annonce: Annonce = Annonce(it.userID, it.title, it.description, it.category, it.type, it.photos, it.createdAt, it._id, it.username)
                        annoncedata.add(annonce)

                    }
                    list_recycler_view.apply {

                        layoutManager = LinearLayoutManager(activity)

                        adapter = ListAdapter(annoncedata)

                    }
                } else {
                    Toast.makeText(view!!.context, "Connexion échoué!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
