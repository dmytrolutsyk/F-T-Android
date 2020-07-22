package com.example.ft_android.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.ft_android.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    data class Annonces(val titre: String, val categorie: String, val type: String, val description: String, val urlImage: String)
    private val annonces = listOf(
        Annonces("Ballon", "Echange", "Sport","Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Roue", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ordinateur", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Pot de fleur", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Robe", "Don", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU"),
        Annonces("Ecran tv", "Echange", "Sport", "Ce ballon est beau", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcThLDbpIIRq0wMmyGsY_DD12fDtdWOKdYHebQ&usqp=CAU")
    )

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
            adapter = ListAdapter(annonces)
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
