package com.evgeny_m.nasaapp.presenter.app_screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.FragmentImageViewerBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageViewerFragment : Fragment() {

    private val args by navArgs<ImageViewerFragmentArgs>()

    private lateinit var binding: FragmentImageViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageViewerBinding.inflate(layoutInflater)

        val download = binding.downloadScreen
        download.visibility = View.VISIBLE
        Glide.with(requireContext())
            .load(R.drawable.load)
            .placeholder(R.drawable.load)
            .into(binding.animation)

        Picasso.get()
            .load(args.uri)
            .into(binding.imageView, object : Callback {
                override fun onSuccess() {
                    download.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Log.d("DEBUG_Error", "ImageViewerFragment error = $e")
                }

            })
        return binding.root
    }

}