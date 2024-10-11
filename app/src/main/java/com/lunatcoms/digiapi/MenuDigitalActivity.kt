package com.lunatcoms.digiapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.lunatcoms.digiapi.databinding.ActivityMenuDigitalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class MenuDigitalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDigitalBinding
    private lateinit var adapter: DigiAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDigitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        adapter = DigiAdapter() { digiId -> navigateToDataDigi(digiId) }
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val callDigi = getRetrofit().create(APIDigi::class.java).getDigimon()
                val digi = callDigi.body()?.content?.map { Digimonster(it.name, it.id, it.image) }
                    ?: emptyList()

                runOnUiThread {
                    if (callDigi.isSuccessful) {
                        Log.i("Mensaje", "Conectado")

                        adapter.updateList(digi)

                    } else {
                        Log.i("Mensake", "No conectado")
                    }

                }


            } catch (e: IOException) {

            }
        }
    }

    private fun navigateToDataDigi(digiId: String) {
        val intent = Intent(this, DataDigiActivity::class.java)
        intent.putExtra(ApiContants.ID_DIGITAL, digiId)
        startActivity(intent)
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiContants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


