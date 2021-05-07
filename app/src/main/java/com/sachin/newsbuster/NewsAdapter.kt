package com.sachin.newsbuster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.*

class NewsAdapter(private val listener : ItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()

    interface ItemClicked {
        fun onItemClicked(item: News)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener(){
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder:NewsViewHolder, position: Int) {

        val currentItem=items[position]
        holder.title.text=currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }

    override fun getItemCount(): Int {

        return items.size
    }

    fun updateNews(updatedNews:ArrayList<News>){

        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title:TextView= itemView.findViewById(R.id.tvTitle)
    val image: ImageView = itemView.findViewById(R.id.ivNews)
}