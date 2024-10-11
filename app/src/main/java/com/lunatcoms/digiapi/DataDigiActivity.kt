package com.lunatcoms.digiapi

import android.graphics.text.LineBreaker
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import com.lunatcoms.digiapi.databinding.ActivityDataDigiBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class DataDigiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataDigiBinding
    private lateinit var digiId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataDigiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        digiId = intent.getStringExtra(ApiContants.ID_DIGITAL) ?: ApiContants.ERROR

        getDataDigi(digiId)
    }

    private fun getDataDigi(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dataDigimonster = getRetrofit().create(APIDigi::class.java).getDataDigimon(id)

                if (dataDigimonster.body() != null){
                    runOnUiThread { createUI(dataDigimonster.body()!!) }
                }
            } catch (e: IOException) {

            }
        }
    }

    private fun createUI(digimonster: DigimonsterDataResponse) {
        Picasso.get().load(digimonster.images[0].urlImage).into(binding.ivImageTop)
        binding.tvName.text = digimonster.name
        binding.tvDescription.text = digimonster.descriptions[1].description
        binding.tvDescription1.text = digimonster.descriptions[1].description

        binding.tvDescription.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiContants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}