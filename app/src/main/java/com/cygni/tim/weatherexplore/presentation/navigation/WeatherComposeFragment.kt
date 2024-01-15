package com.cygni.tim.weatherexplore.presentation.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.cygni.tim.weatherexplore.data.models.Point
import com.cygni.tim.weatherexplore.databinding.WeatherFragmentBinding
import com.cygni.tim.weatherexplore.presentation.compose.WeatherScreen
import com.cygni.tim.weatherexplore.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherComposeFragment : Fragment() {

    private var _binding: WeatherFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModels()

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
            WeatherScreen(viewModel = viewModel, onNavigateToMap = { point ->
                if (!navigateToMap(point)) {
                    viewModel.addMessage(WeatherViewModel.Message.FailedToNavigateToMapMessage)
                }
            }, onDismissMessage = { message ->
                viewModel.clearMessage(message)
            })
        }
        return binding.root
    }

    private fun navigateToMap(point: Point): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:${point.lat},${point.lon}"))
        //mapIntent.setPackage("com.google.android.apps.maps")
        return try {
            startActivity(intent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }
}