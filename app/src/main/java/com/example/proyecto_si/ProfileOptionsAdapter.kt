package com.example.proyecto_si

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ProfileOptionsAdapter(private val photos: List<Int>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ProfileOptionsAdapter.ProfileOptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_option, parent, false)
        return ProfileOptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileOptionViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
        holder.itemView.setOnClickListener {
            onItemClick(photo)
        }
    }

    override fun getItemCount(): Int = photos.size

    inner class ProfileOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewOption: ImageView = itemView.findViewById(R.id.imageViewOption1)

        fun bind(photo: Int) {
            imageViewOption.setImageResource(photo)
        }
    }
}
