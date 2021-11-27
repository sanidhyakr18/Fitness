package com.sandystudios.fitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val adapter = CardioAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.cardioRV).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { ApiClient.api.getCardio() }
            if (response.isSuccessful) {
                response.body()?.let { response1 ->
                    response1.data?.let {
                        adapter.data = it as List<DataItem>
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}