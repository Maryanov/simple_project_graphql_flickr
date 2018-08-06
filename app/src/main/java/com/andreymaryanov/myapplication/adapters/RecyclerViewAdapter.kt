package com.andreymaryanov.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.andreymaryanov.myapplication.R
import com.andreymaryanov.myapplication.common.AppConst.TYPE_ITEM_FLICK
import com.andreymaryanov.myapplication.common.AppConst.TYPE_ITEM_GITHUB
import com.andreymaryanov.myapplication.models.ListFeed
import com.bumptech.glide.Glide

class RecyclerViewAdapter(private val context: Context, private val items: ArrayList<ListFeed>, private val listener:RecyclerViewClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        var v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_github, viewGroup, false)
        when (viewType) {
            TYPE_ITEM_FLICK-> v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_flicker, viewGroup, false)
        }
        v.setOnClickListener({ _ -> listener.onItemClick(v) })
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val type = getItemViewType(position)

        when (type) {
            TYPE_ITEM_GITHUB -> {
                viewHolder.name = viewHolder.itemView.findViewById(R.id.textName) as TextView
                viewHolder.name.text =items[position].name
            }
            TYPE_ITEM_FLICK -> {
                viewHolder.image = viewHolder.itemView.findViewById(R.id.image) as ImageView
                Glide.with(context).load(items[position].imageUrl)
                        .into(viewHolder.image)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position%2==0) return TYPE_ITEM_GITHUB
        return TYPE_ITEM_FLICK
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var image: ImageView
        lateinit var name: TextView
    }

}
