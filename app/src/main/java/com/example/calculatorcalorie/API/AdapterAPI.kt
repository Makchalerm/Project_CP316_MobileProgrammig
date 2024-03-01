package com.example.calculatorcalorie.API

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorcalorie.R
import com.squareup.picasso.Picasso

class AdapterAPI (private val postList: List<calorie>, private val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_api,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name!!.text = postList[position].name
        holder.slug!!.text = postList[position].slug
        holder.abbr!!.text = postList[position].abbr
        Picasso.get().load(postList[position].light)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.light)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
