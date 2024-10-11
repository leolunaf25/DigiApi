package com.lunatcoms.digiapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lunatcoms.digiapi.databinding.ItemDigimonsterBinding
import com.squareup.picasso.Picasso

class DigiViewHolder(
    view: View,
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemDigimonsterBinding.bind(view)

    fun bind(item: Digimonster) {

        binding.tvName.text = item.name
        binding.tvId.text = item.id

        Picasso.get().load(item.image).into(binding.ivView)
        binding.btnDigimonster.setOnClickListener { onItemSelected(item.id) }
    }
}