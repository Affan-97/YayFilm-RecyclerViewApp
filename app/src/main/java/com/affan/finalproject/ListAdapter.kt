package com.affan.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(private val ListData:ArrayList<Movies>):
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback:OnItemClickCallback
fun setOnItemClickCallback(isClicked:OnItemClickCallback){
    this.onItemClickCallback = isClicked
}
    interface OnItemClickCallback {
fun onCLicked(data:Movies)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
val image:ImageView = itemView.findViewById(R.id.imgCover)
val title:TextView = itemView.findViewById(R.id.tvTitle)
val releaseDate:TextView = itemView.findViewById(R.id.tvDate)
val director:TextView = itemView.findViewById(R.id.tvDirector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val listDisp:View =LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ListViewHolder(listDisp)

    }

    override fun getItemCount(): Int = ListData.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
val (title,director,releaseDate,description,image) = ListData[position]
Glide.with(holder.itemView.context).load(image).into(holder.image)
        holder.title.text = title
        holder.releaseDate.text = releaseDate
        holder.director.text = director
        holder.itemView.setOnClickListener{onItemClickCallback.onCLicked(ListData[holder.adapterPosition])}
    }
}