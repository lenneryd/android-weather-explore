package com.cygni.tim.weatherexplore.presentation.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.cygni.tim.weatherexplore.databinding.ClockFragmentBinding
import com.cygni.tim.weatherexplore.databinding.WeatherFragmentBinding
import com.cygni.tim.weatherexplore.presentation.compose.ClockScreen
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherComposeFragment: Fragment() {

    private var _binding: WeatherFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)

        sharedElementReturnTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherFragmentBinding.inflate(inflater, container, false)

        binding.weatherCompose.setContent {
            WeatherScreen(viewModel = viewModel())
        }
        return binding.root
    }
}