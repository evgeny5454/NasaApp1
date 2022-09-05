package com.evgeny_m.nasaapp.presenter.app_screens.image_viewer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.FragmentBaseViewerBinding
import com.evgeny_m.nasaapp.presenter.app_screens.apod.pages.PicturesArchiveAdapter
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModel
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModelFactory


class BaseViewerFragment : Fragment() {

    private lateinit var binding: FragmentBaseViewerBinding
    private lateinit var viewModel: ApodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseViewerBinding.inflate(layoutInflater)



        val viewerPager2 = binding.viewPager2


        viewModel = ViewModelProvider(
            this,
            ApodViewModelFactory(requireContext())
        )[ApodViewModel::class.java]

        val adapter = ViewerPager2Adapter(requireContext())
        viewerPager2.setPageTransformer(ZoomOutPageTransformer())
        viewerPager2.adapter = adapter

        viewModel.getCashList()

        val download = binding.downloadScreen
        Glide.with(this)
            .load(R.drawable.load)
            .placeholder(R.drawable.load)
            .into(binding.animation)
            .clearOnDetach()
        download.visibility = View.VISIBLE

        viewModel.cashList.observe(viewLifecycleOwner) {
            Log.d("ITEMSSSSS", it.toString())
            adapter.addDownItems(it)
            download.visibility = View.GONE
        }
        return binding.root
    }

}