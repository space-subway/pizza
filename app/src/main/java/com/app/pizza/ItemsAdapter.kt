package com.app.pizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pizza.model.Item

class ItemsAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView              : ImageView = view.findViewById(R.id.image_view)
        var tittleTextView         : TextView  = view.findViewById(R.id.title)
        var priceButton            : TextView  = view.findViewById(R.id.price)
        var descriptionTextView    : TextView  = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[ position ]

        if( position % 2 == 0 )  holder.imageView.setImageResource(R.drawable.pizza_1)
        else holder.imageView.setImageResource(R.drawable.pizza_2)

        holder.tittleTextView.text      = item.title
        holder.priceButton.text         = "от " + item.price.toDouble() + " р"
        holder.descriptionTextView.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }
}