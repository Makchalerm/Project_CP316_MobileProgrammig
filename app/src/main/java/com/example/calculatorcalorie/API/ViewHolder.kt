package com.example.calculatorcalorie.API

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatorcalorie.R

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val name : TextView? = itemView.findViewById(R.id.nameView)
    val slug: TextView? = itemView.findViewById(R.id.slugView)
    val abbr: TextView? = itemView.findViewById(R.id.abbrView)
    val light: ImageView? = itemView.findViewById(R.id.logoView)
}
