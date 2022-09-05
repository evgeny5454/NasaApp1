package com.evgeny_m.nasaapp.presenter.app_screens.pages

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeny_m.data.utils.MediaType
import com.evgeny_m.domain.model.Item
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.ItemPictureBinding
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
                    picture.visibility = View.VISIBLE
                    videoPlayButton.visibility = View.GONE
                    web.visibility = View.GONE
                    titleWeb.visibility = View.GONE

                    Glide.with(context)
                        .load(image.urlImagePreview)
                        .centerCrop()
                        .into(picture)
                    item.setOnClickListener { view ->
                        /*val action = ApodBaseFragmentDirections
                            .actionApodBaseFragmentToImageViewerFragment(image.url)*/
                        view.findNavController().navigate(R.id.action_apodBaseFragment_to_baseViewerFragment)
                    }
                }
                MediaType.Video.type -> {
                    picture.visibility = View.VISIBLE
                    videoPlayButton.visibility = View.VISIBLE
                    web.visibility = View.GONE
                    titleWeb.visibility = View.GONE

                    Glide.with(context)
                        .load(image.urlImagePreview)
                        .centerCrop()
                        .into(picture)
                    item.setOnClickListener {
                        val uri = Uri.parse(image.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                }
                MediaType.Web.type -> {
                    picture.visibility = View.VISIBLE
                    videoPlayButton.visibility = View.GONE
                    web.visibility = View.VISIBLE
                    titleWeb.visibility = View.VISIBLE

                    titleWeb.text = image.title
                    item.setOnClickListener {
                        val uri = Uri.parse(image.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
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
        notifyItemRangeInserted(images.size, listData.size)
    }

    fun getLastDate(): LocalDate {
        return images.last().date
    }
}