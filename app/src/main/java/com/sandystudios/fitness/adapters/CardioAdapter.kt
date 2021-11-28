package com.sandystudios.fitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandystudios.fitness.R
import com.sandystudios.fitness.models.DataItem

class CardioAdapter : RecyclerView.Adapter<CardioAdapter.MyViewHolder>() {

    var data: List<DataItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardio, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DataItem) = with(itemView) {
            findViewById<TextView>(R.id.name).text = item.name?.uppercase()
            findViewById<TextView>(R.id.trainer_name).text = ("With " + item.trainerName?.uppercase())
            findViewById<TextView>(R.id.duration).text = ((item.duration?.div(60)).toString() + " Min")
            findViewById<TextView>(R.id.difficulty).text = item.difficultyLevelName
//            Picasso.get().load(item.image).into(findViewById<ImageView>(R.id.imageView))
        }
    }
}