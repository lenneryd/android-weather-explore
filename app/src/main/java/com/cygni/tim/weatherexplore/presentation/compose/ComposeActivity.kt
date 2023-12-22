package com.cygni.tim.weatherexplore.presentation.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppYuTheme {
                MainScreenComposable(
                    clockContent = { ClockScreen(viewModel = viewModel()) },
                    colorsContent = { ColorsScreenComposable() }
                )
            }
        }
    }
}

@Preview
@Composable
fun ComposeActivityPreview() {
    AppYuTheme {
        MainScreenComposable(
            clockContent = { ClockScreenComposable(state = clockPreviewState()) },
            colorsContent = { ColorsScreenComposable() }
        )
    }
}