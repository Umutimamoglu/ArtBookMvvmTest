package com.hope.artbookmvvmtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hope.artbookmvvmtest.R
import com.hope.artbookmvvmtest.roomdb.Art
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    class ImageViewHolder (itemView : View): RecyclerView.ViewHolder(itemView)

    private var onItemClickListener : ((String) -> Unit) ? = null


    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(p0: String, p1: String): Boolean {
            return p0 == p1
        }

        override fun areContentsTheSame(p0: String, p1: String): Boolean {
            return p0 == p1
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var images: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.image_row,p0,false)
        return ImageViewHolder(view)
    }

    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(p0: ImageViewHolder, p1: Int) {
        val imageView = p0.itemView.findViewById<ImageView>(R.id.singleArtImageView)
        val url = images[p1]
        p0.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}