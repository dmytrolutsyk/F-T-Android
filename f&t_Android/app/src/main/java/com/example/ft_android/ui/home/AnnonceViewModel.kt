package com.example.ft_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ft_android.R

class AnnonceViewModel (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_categorie)
    }

    fun bind(annonces: HomeFragment.Annonces) {
        mTitleView?.text = annonces.titre
        mYearView?.text = annonces.categorie
    }
}

class ListAdapter(private val list: List<HomeFragment.Annonces>)
    : RecyclerView.Adapter<AnnonceViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return AnnonceViewModel(inflater, parent)
    }

    override fun onBindViewHolder(holder: AnnonceViewModel, position: Int) {
        val annonces: HomeFragment.Annonces = list[position]
        holder.bind(annonces)
    }

    override fun getItemCount(): Int = list.size

}