package com.evgeny_m.asteroids_neows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.evgeny_m.asteriods_neows.R
import com.evgeny_m.asteriods_neows.databinding.FragmentAsteroidsNeoWsBinding

class AsteroidsNeoWsFragment : Fragment() {

    private lateinit var binding: FragmentAsteroidsNeoWsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAsteroidsNeoWsBinding.inflate(layoutInflater)
        //val navigationController = findNavController(this)

        return binding.root
    }

}