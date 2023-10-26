package com.example.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.superheroapp.SuperHeroListActivity.Companion.EXTRA_ID
import com.example.superheroapp.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val heroResponse = getRetrofit().create(ApiService::class.java).getSuperheroInformation(id)
                if (heroResponse.body() != null){
                    // Solo el hilo principal puede modificar la UI
                    // con runOnUiThread se ejecuta en el hilo principal desde hilo secundario
                    runOnUiThread {
                        createUI(heroResponse.body()!!) // !! me aseguro que no es nulo
                    }
                }

        }
    }

    private fun createUI(superheroItem: SuperheroItem) {
        Picasso.get().load(superheroItem.image.url).into(binding.ivSuperheroDetail) // carga imagen en imageview

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}