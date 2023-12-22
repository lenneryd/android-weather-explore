package com.cygni.tim.weatherexplore.presentation.compose

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.presentation.colors.AppYuTheme
import kotlinx.coroutines.launch

enum class Views {
    Clock, Colors, None
}

@Composable
fun MainScreenComposable(
    clockContent: @Composable () -> Unit,
    colorsContent: @Composable () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var showView by remember { mutableStateOf(Views.Clock) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            // Topbar
            // background = Uses Primary for light, and surface for dark by default.
            // content = Uses onPrimary for light, and onSurface for dark by default.
            TopAppBar(
                title = { Text("Compose Playbox") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply { if (isClosed) open() else close() }
                        }
                    }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu Icon",
                        )
                    }
                })
        },
        // Drawer
        // background = Uses surface color by default on both light and dark.
        // scrim = Uses onSurface with alpha.
        drawerContent = {
            Text(
                text = "Playground Items", fontSize = 24.sp, modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)

            )
            DrawerItem(iconResource = R.drawable.clock_outline, title = "Clock Example") {
                if (showView != Views.Clock) {
                    showView = Views.Clock
                }
                scope.launch { scaffoldState.drawerState.apply { close() } }
            }
            Divider(startIndent = 16.dp, modifier = Modifier.padding(end = 16.dp))
            DrawerItem(
                iconResource = R.drawable.information_outline,
                title = "Material You Colors"
            ) {
                if (showView != Views.Colors) {
                    showView = Views.Colors
                }
                scope.launch { scaffoldState.drawerState.apply { close() } }
            }
            Divider(startIndent = 16.dp, modifier = Modifier.padding(end = 16.dp))
        }
    ) { padding ->
        Crossfade(targetState = showView, label = "MainContentSwitchFade", modifier = Modifier.padding(padding)) { showView ->
            when (showView) {
                Views.Clock -> clockContent()
                Views.Colors -> colorsContent()
                Views.None -> {}
            }
        }
    }
}

@Composable
fun DrawerItem(iconResource: Int, title: String, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp)
        .clickable { onClick() }) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = title,
            modifier = Modifier
                .size(48.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = title,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppYuTheme {
        MainScreenComposable(
            clockContent = { ClockScreenComposable(state = clockPreviewState()) },
            colorsContent = { ColorsScreenComposable() }
        )
    }
}