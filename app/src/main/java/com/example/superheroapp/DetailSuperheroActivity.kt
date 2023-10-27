package com.example.superheroapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.example.superheroapp.SuperHeroListActivity.Companion.EXTRA_ID
import com.example.superheroapp.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

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
        binding.tvDetailSuperheroName.text = superheroItem.name
        prepareStats(superheroItem.powerstats)
        binding.tvDetailSuperheroRealName.text = superheroItem.biography.fullName
        binding.tvDetailSuperheroPublisher.text = superheroItem.biography.publisher

    }

    private fun prepareStats(powerstats:Powerstats) {

        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewStrength, powerstats.strength)
    }

    private fun updateHeight(view:View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}