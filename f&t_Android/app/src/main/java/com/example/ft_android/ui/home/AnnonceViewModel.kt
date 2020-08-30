package com.example.ft_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ft_android.Annonce
import com.example.ft_android.R

class AnnonceViewModel (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_categorie)
    }

    fun bind(annonces: Annonce) {
        mTitleView?.text = annonces.title
        mYearView?.text = annonces.category
    }
}

class ListAdapter(private val list: ArrayList<Annonce>)
    : RecyclerView.Adapter<AnnonceViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnonceViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return AnnonceViewModel(inflater, parent)
    }

    override fun onBindViewHolder(holder: AnnonceViewModel, position: Int) {
        val annonces: Annonce = list.elementAt(position)
        holder.bind(annonces)
    }

    override fun getItemCount(): Int = list.size

}