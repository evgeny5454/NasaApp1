package com.evgeny_m.nasaapp.presenter.app_screens.image_viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.FragmentBaseViewerBinding
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModel
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator


class BaseViewerFragment : Fragment() {
    private lateinit var binding: FragmentBaseViewerBinding
    private lateinit var viewModel: ApodViewModel

    private val args by navArgs<BaseViewerFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseViewerBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            ApodViewModelFactory(requireContext())
        )[ApodViewModel::class.java]

        val viewerPager2 = binding.viewPager2
        val adapter = ViewerPager2Adapter(requireContext(), viewModel)
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
            adapter.addDownItems(it)
            viewerPager2.setCurrentItem(args.currentItem, false)
            download.visibility = View.GONE

            TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
                tab.text = it[position].date.toString()
            }.attach()

        }
        viewModel.stateAdapter.observe(viewLifecycleOwner) {
            viewerPager2.isUserInputEnabled = it
        }

        binding.toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.download -> {
                    val position : Int = viewerPager2.currentItem
                    val item = adapter.getItem(position)
                    viewModel.downloadImage(item.url, item.title)
                    //Toast.makeText(requireContext(), "${item.date}\n${item.url}", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        return binding.root
    }
}