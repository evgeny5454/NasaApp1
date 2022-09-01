package com.evgeny_m.nasaapp.presenter.app_screens.pages

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeny_m.domain.model.Item
import com.evgeny_m.nasaapp.databinding.ItemPictureBinding
import com.evgeny_m.nasaapp.utilits.MediaType
import com.evgeny_m.nasaapp.utilits.getYoutubeImage
import java.time.LocalDate

class PicturesAdapter(private val context: Context) :
    RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {

    private var images: MutableList<Item> = mutableListOf()

    class PictureViewHolder(val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPictureBinding.inflate(inflater, parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val image = images[position]
        with(holder.binding) {
            date.text = image.date.toString()
            when (image.media_type) {
                MediaType.Image.type -> {
                    videoPlayButton.visibility = View.GONE

                    Glide.with(context)
                        .load(image.url)
                        //.thumbnail(0.33f)
                        .centerCrop()
                        .into(picture)
                }
                MediaType.Video.type -> {
                    videoPlayButton.visibility = View.VISIBLE

                    Glide.with(context)
                        .load(getYoutubeImage(image.url))
                        //.thumbnail(0.33f)
                        .centerCrop()
                        .into(picture)
                }

                else -> {}
            }

        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun addDownItems(listData: List<Item>) {
        if (images.isEmpty()) {
            images.addAll(listData)
        } else {
            images.addAll(images.size, listData)
        }
        notifyDataSetChanged()
    }

    fun deleteItems(datesCount: Int) {
        for (i in 1..datesCount) {
            images.removeFirst()
        }
    }

    fun getLastDate() : LocalDate {
        return images.last().date
    }
    fun getFirstDate() : LocalDate {
        return images.first().date
    }

}