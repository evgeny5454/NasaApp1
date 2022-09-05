package com.evgeny_m.nasaapp.presenter.app_screens.image_viewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.evgeny_m.nasaapp.databinding.FragmentBaseViewerBinding
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

        viewModel = ViewModelProvider(
            this,
            ApodViewModelFactory(requireContext())
        )[ApodViewModel::class.java]


        viewModel.archiveList.observe(viewLifecycleOwner, Observer {


        })

        return binding.root
    }

}