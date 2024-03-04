package com.cygni.tim.weatherexplore.presentation.compose

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.presentation.viewmodel.DocumentScanViewModel

@Composable
fun DocumentScanScreen(
    state: DocumentScanViewModel.DocumentScanUIState, scanClicked: () -> Unit = {}, retryClicked: () -> Unit = {}
) {
    when (state) {
        is DocumentScanViewModel.DocumentScanUIState.ReadyToScanState -> ReadyToScan(scanClicked)
        is DocumentScanViewModel.DocumentScanUIState.ScanningErrorState -> ScanError(state, retryClicked)
        is DocumentScanViewModel.DocumentScanUIState.ScanResult -> ScanResult(state, retryClicked)
        else -> {}
    }
}

@Composable
fun ReadyToScan(scanClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Button(onClick = { scanClicked() }, modifier = Modifier.padding(top = 8.dp)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Scan document", modifier = Modifier.padding(end = 8.dp))
            Text(
                text = "Scan document"
            )
        }
    }
}

@Composable
fun ScanError(state: DocumentScanViewModel.DocumentScanUIState.ScanningErrorState, retryClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = "Error: ${state.error.message}"
        )
        Button(onClick = { retryClicked() }, modifier = Modifier.padding(top = 8.dp)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Retry", modifier = Modifier.padding(end = 8.dp))
            Text(
                text = "Retry"
            )
        }
    }
}

@Composable
fun ScanResult(state: DocumentScanViewModel.DocumentScanUIState.ScanResult, scanAgainClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                .data(state.uri)
                .placeholder(R.drawable.image)
                .build()),
            contentDescription = "Scanned Image",
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(10f / 16f)
                .padding(bottom = 24.dp)
        )
        Button(onClick = { scanAgainClicked() }, modifier = Modifier.padding(top = 8.dp)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Retry", modifier = Modifier.padding(end = 8.dp))
            Text(
                text = "Scan again"
            )
        }
    }
}

@Preview
@Composable
fun DocumentScanScreenPreview() {
    DocumentScanScreen(state = DocumentScanViewModel.DocumentScanUIState.ReadyToScanState(true))
}


@Preview
@Composable
fun DocumentScanErrorScreenPreview() {
    DocumentScanScreen(
        state = DocumentScanViewModel.DocumentScanUIState.ScanningErrorState(
            Exception("Failed to scan document")
        )
    )
}


@Preview
@Composable
fun DocumentScanResultScreenPreview() {
    DocumentScanScreen(
        state = DocumentScanViewModel.DocumentScanUIState.ScanResult(Uri.EMPTY)
    )
}
