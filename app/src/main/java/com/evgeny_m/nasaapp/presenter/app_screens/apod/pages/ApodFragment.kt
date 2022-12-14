package com.evgeny_m.nasaapp.presenter.app_screens.apod.pages

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.evgeny_m.data.utils.MediaType
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.FragmentApodBinding
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModel
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModelFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class ApodFragment : Fragment() {

    private lateinit var binding: FragmentApodBinding
    private lateinit var viewModel: ApodViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBinding.inflate(layoutInflater)

        binding.videoPlayButton.visibility = View.GONE

        viewModel = ViewModelProvider(
            this,
            ApodViewModelFactory(requireContext())
        )[ApodViewModel::class.java]
        viewModel.downloadItem()

        viewModel.item.observe(viewLifecycleOwner) {

            if (it != null) {
                when (it.media_type) {
                    MediaType.Image.type -> {
                        binding.copyright.visibility = View.VISIBLE
                        binding.copyright.text = it.copyright

                        Glide.with(requireActivity())
                            .load(it.urlImagePreview)
                            .into(binding.image)
                    }
                    MediaType.Video.type -> {
                        binding.videoPlayButton.visibility = View.VISIBLE
                        binding.copyright.visibility = View.GONE
                        Glide.with(requireActivity())
                            .load(it.urlImagePreview)
                            .into(binding.image)
                    }
                }

                binding.date.text = it.date.toString()
                binding.explanation.text = it.explanation
                binding.title.text = it.title
            } else {
                viewModel.downloadItem()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ApodFragment()
    }
}