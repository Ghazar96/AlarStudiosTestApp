package com.example.alarstudiostestapp.mainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarstudiostestapp.PlacesData
import com.example.alarstudiostestapp.R

class MainAdapter(
    private val items: ArrayList<PlacesData>,
    val clickListener: (PlacesData) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun addItems(items: List<PlacesData>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: PlacesData = items[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(data: PlacesData) {
            val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
            val countryTextView = itemView.findViewById<TextView>(R.id.countryTextView)

            nameTextView.text = data.name
            countryTextView.text = data.country
            itemView.setOnClickListener {
                clickListener.invoke(data)
            }
        }
    }
}