package com.sandystudios.fitness

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private var originalList = arrayListOf<DataItem>()
    private val adapter = CardioAdapter()

    lateinit var difficulty: String

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

    private val difficultySpinner: Spinner by lazy {
        findViewById(R.id.difficulty_spinner)
    }

    private val difficulty_labels = arrayListOf("Any", "Beginner", "Intermediate", "Advanced")

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
            difficulty = difficultySpinner.selectedItem.toString()
            Log.d(TAG, difficulty)
            filterDrawerLayout.closeDrawer(filterView)
            loadItems()
        }

        setUpSpinner()

        loadItems()
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter(this, R.layout.item_spinner, R.id.text, difficulty_labels)
        difficultySpinner.adapter = adapter
        difficulty = difficultySpinner.selectedItem.toString()
    }

    override fun onBackPressed() {
        if (filterDrawerLayout.isDrawerOpen(filterView)) {
            filterDrawerLayout.closeDrawer(filterView)
        } else {
            super.onBackPressed()
        }
    }

    fun loadItems() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { ApiClient.api.getCardio() }
                if (response.isSuccessful) {
                    response.body()?.let { response1 ->
                        response1.data?.let { it ->
                            originalList.clear()
                            originalList = it as ArrayList<DataItem>
                            if(difficulty != "Any") {
                                val getList = originalList.filter {
                                    it.difficultyLevelName!!.contains(difficulty, true)
                                }
                                adapter.data = getList
                                adapter.notifyDataSetChanged()
                            } else {
                                adapter.data = originalList
                                adapter.notifyDataSetChanged()
                            }

                        }
                    }
                }
            } catch (e: Exception) {
                adapter.data = emptyList()
                adapter.notifyDataSetChanged()
            }
        }
    }
}