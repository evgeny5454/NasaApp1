package com.evgeny_m.nasaapp.presenter.app_screens.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evgeny_m.nasaapp.databinding.FragmentApodBaseBinding
import com.evgeny_m.nasaapp.presenter.app_screens.apod.pages.ApodFragment
import com.evgeny_m.nasaapp.presenter.app_screens.apod.pages.PicturesArchiveFragment
import com.google.android.material.tabs.TabLayoutMediator

class ApodBaseFragment : Fragment() {
    private val fragmentList = listOf(
        ApodFragment.newInstance(),
        PicturesArchiveFragment.newInstance()
    )
    private val fragmentListTitles = listOf(
        "Picture of the Day",
        "Pictures archive"
    )
    private lateinit var binding: FragmentApodBaseBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBaseBinding.inflate(layoutInflater)

        val adapter = ViewPagerAdapter(requireActivity(), fragmentList)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = fragmentListTitles[position]
        }.attach()

        return binding.root
    }

}