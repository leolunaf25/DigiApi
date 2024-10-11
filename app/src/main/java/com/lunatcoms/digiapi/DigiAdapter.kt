package com.lunatcoms.digiapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DigiAdapter(
    private var digimonster: List<Digimonster> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<DigiViewHolder>() {

    fun updateList(digimonster: List<Digimonster>) {
        this.digimonster = digimonster
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DigiViewHolder(
            layoutInflater.inflate(R.layout.item_digimonster, parent, false), onItemSelected)
    }

    override fun getItemCount(): Int = digimonster.size

    override fun onBindViewHolder(holder: DigiViewHolder, position: Int) {
        val item = digimonster[position]
        holder.bind(item)
    }

}