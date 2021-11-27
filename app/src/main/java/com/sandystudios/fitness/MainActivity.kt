package com.sandystudios.fitness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private val adapter = CardioAdapter()

    private val toolbar: MaterialToolbar by lazy {
        findViewById(R.id.appBarLayout)
    }

    private val drawerLayout: DrawerLayout by lazy {
        findViewById(R.id.drawer_layout)
    }

    private val navView: NavigationView by lazy {
        findViewById(R.id.nav_view)
    }

    private val filterBtn: ShapeableImageView by lazy {
        findViewById(R.id.filter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<RecyclerView>(R.id.cardioRV).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        filterBtn.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(navView))
                drawerLayout.openDrawer(navView)
            else drawerLayout.closeDrawer(navView)
        }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { ApiClient.api.getCardio() }
                if (response.isSuccessful) {
                    response.body()?.let { response1 ->
                        response1.data?.let {
                            adapter.data = it as List<DataItem>
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            } catch (e: Exception) {
                adapter.data = emptyList()
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }
}