package com.example.ytest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ytest.R
import com.example.ytest.data.local.Product

class ViewPagerAdapter(val items : List<Product>): RecyclerView.Adapter<ViewPagerAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder =
        viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
//        holder.viewName.text = views[position]
    }

    class viewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val viewName: TextView = view.findViewById(R.id.view_name)
    }
}