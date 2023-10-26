package com.example.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItem: SuperheroItem){
        binding.tvSuperheroName.text = superheroItem.name // amarro nombre con textView
        Picasso.get().load(superheroItem.image.url).into(binding.ivSuperhero) // carga imagen en imageview
    }
}