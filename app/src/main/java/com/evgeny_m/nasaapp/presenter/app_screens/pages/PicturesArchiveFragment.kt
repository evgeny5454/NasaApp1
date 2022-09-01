package com.evgeny_m.nasaapp.presenter.app_screens.pages

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeny_m.nasaapp.R
import com.evgeny_m.nasaapp.databinding.FragmentPicturesArchiveBinding
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModel
import com.evgeny_m.nasaapp.presenter.view_model.ApodViewModelFactory

@Suppress("DEPRECATION")
class PicturesArchiveFragment : Fragment() {

    private lateinit var binding: FragmentPicturesArchiveBinding
    private lateinit var viewModel: ApodViewModel
    private lateinit var adapter: PicturesAdapter


    lateinit var downLoadScreen: FrameLayout

    //var load = false
    var loading = false
    //var isUp = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPicturesArchiveBinding.inflate(layoutInflater)

        downLoadScreen = binding.downloadScreen

        Glide.with(requireContext())
            .load(R.drawable.load)
            .into(binding.animation)

        downLoadScreen.visibility = View.VISIBLE


        viewModel = ViewModelProvider(
            this,
            ApodViewModelFactory(requireContext())
        )[ApodViewModel::class.java]

        if (savedInstanceState == null) {
            viewModel.downloadArchiveList(null)
        }
        adapter = PicturesAdapter(requireContext())
        val recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        viewModel.archiveList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.addDownItems(it)
                loading = true
                downLoadScreen.visibility = View.INVISIBLE
            }
        }

        binding.recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (layoutManager.findFirstVisibleItemPosition() >= adapter.itemCount / 2
                        && loading
                    ) {
                        loading = false
                        viewModel.downloadArchiveList(date = adapter.getLastDate())
                    } else if (layoutManager.findLastVisibleItemPosition() + 2 >= adapter.itemCount) {
                        downLoadScreen.visibility = View.VISIBLE
                    }
                }
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PicturesArchiveFragment()
    }
}