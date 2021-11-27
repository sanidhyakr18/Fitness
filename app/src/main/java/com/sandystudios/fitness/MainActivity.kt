package com.sandystudios.fitness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val adapter = CardioAdapter()

    private val filterDrawerLayout: DrawerLayout by lazy {
        findViewById(R.id.filter_drawer_layout)
    }

    private val filterView: NavigationView by lazy {
        findViewById(R.id.filter_view)
    }

    private val imgFilter: ShapeableImageView by lazy {
        findViewById(R.id.img_filter)
    }

    private val imgApply: ShapeableImageView by lazy {
        findViewById(R.id.img_apply)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.cardioRV).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = this@MainActivity.adapter
        }

        imgFilter.setOnClickListener {
            if (!filterDrawerLayout.isDrawerOpen(filterView))
                filterDrawerLayout.openDrawer(filterView)
            else filterDrawerLayout.closeDrawer(filterView)
        }

        imgApply.setOnClickListener {
            filterDrawerLayout.closeDrawer(filterView)
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
        if (filterDrawerLayout.isDrawerOpen(filterView)) {
            filterDrawerLayout.closeDrawer(filterView)
        } else {
            super.onBackPressed()
        }
    }
}