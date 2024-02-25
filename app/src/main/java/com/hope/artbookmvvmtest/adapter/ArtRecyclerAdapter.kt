package com.hope.artbookmvvmtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.hope.artbookmvvmtest.R
import com.hope.artbookmvvmtest.roomdb.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class  ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Art>(){
        override fun areItemsTheSame(p0: Art, p1: Art): Boolean {
            return p0 == p1
        }

        override fun areContentsTheSame(p0: Art, p1: Art): Boolean {
            return p0 == p1
        }

    }

    private val recylcerListDiffer = AsyncListDiffer(this,diffUtil)

    var arts: List<Art>
        get() = recylcerListDiffer.currentList
        set(value) = recylcerListDiffer.submitList(value)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ArtViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.art_row,p0,false)
        return ArtViewHolder(view)
    }


    override fun onBindViewHolder(p0: ArtViewHolder, p1: Int) {
        val imageView = p0.itemView.findViewById<ImageView>(R.id.artRowImageView)
        val nameText= p0.itemView.findViewById<TextView>(R.id.artRowNameText)
        val artistNameText = p0.itemView.findViewById<TextView>(R.id.artistNameText)
        val yearText= p0.itemView.findViewById<TextView>(R.id.yearText)
        val art = arts[p1]
        p0.itemView.apply {
            nameText.text = "Name: ${art.name}"
            artistNameText.text = "Artist Name : ${art.artistName}"
            yearText.text = "Year: ${art.year}"
            glide.load(art.imageUrl).into(imageView)
        }
    }


    override fun getItemCount(): Int {
        return  arts.size
    }
}