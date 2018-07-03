package com.example.surya.roomapp.RoomAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.surya.roomapp.R
import com.example.surya.roomapp.WeatherData
import kotlinx.android.synthetic.main.note_card.view.*

class RoomAdap(context: Context,private var items: List<WeatherData>): RecyclerView.Adapter<RoomAdap.ViewHolder>()
{
    var context: Context

    init {
        this.context = context
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notedesc: String = items.get(position).humidity
        holder.notedescription?.text = notedesc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v =  LayoutInflater.from(parent.context).inflate(R.layout.note_card,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var notedescription = view.notedesc
    }
}