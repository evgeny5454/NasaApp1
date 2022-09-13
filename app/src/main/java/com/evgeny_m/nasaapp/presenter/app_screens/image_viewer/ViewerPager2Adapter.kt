package com.evgeny_m.nasaapp.presenter.app_screens.image_viewer

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeny_m.domain.model.Item
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.ItemPictureFullScreenBinding
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModel
import com.ortiz.touchview.OnTouchImageViewListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ViewerPager2Adapter(private val context: Context, private val viewModel: ApodViewModel) :

    RecyclerView.Adapter<ViewerPager2Adapter.ImagesViewHolder>() {


    private var images: MutableList<Item> = mutableListOf()

    class ImagesViewHolder(val binding: ItemPictureFullScreenBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPictureFullScreenBinding.inflate(inflater, parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val image = images[position]
        with(holder.binding) {
            touchImageView.resetZoom()
            downloadScreen.visibility = View.VISIBLE

            Glide.with(context)
                .load(R.drawable.load)
                .placeholder(R.drawable.load)
                .into(animation)
                .clearOnDetach()

            Picasso.get()
                .load(image.urlImagePreview)
                .into(touchImageView, object : Callback {
                    override fun onSuccess() {
                        downloadScreen.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        Log.d("DEBUG_Error", "ImageViewerFragment error = $e")
                    }
                })
            touchImageView.setOnTouchImageViewListener(object : OnTouchImageViewListener {
                val currentZoom = touchImageView.currentZoom
                override fun onMove() {
                    if (currentZoom != touchImageView.currentZoom) {
                        viewModel.setState(false)
                    } else {
                        viewModel.setState(true)
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun addDownItems(listData: List<Item>) {
        images.addAll(images.size, listData)
        notifyItemInserted(images.size)

    }

    fun getItem(position: Int) : Item {
        return images[position]
    }
}