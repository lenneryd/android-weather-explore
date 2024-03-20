package com.cygni.tim.weatherexplore.presentation.compose

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cygni.tim.weatherexplore.R
import com.cygni.tim.weatherexplore.presentation.viewmodel.DocumentScanViewModel

@Composable
fun DocumentScanScreen(
    state: DocumentScanViewModel.DocumentScanUIState, scanClicked: () -> Unit = {}, generateOCRClicked: (DocumentScanViewModel.OCRSource) -> Unit = {}, retryClicked: () -> Unit = {}
) {
    when (state) {
        is DocumentScanViewModel.DocumentScanUIState.ReadyToScanState -> ReadyToScan(scanClicked)
        is DocumentScanViewModel.DocumentScanUIState.ScanningErrorState -> ScanError(state, retryClicked)
        is DocumentScanViewModel.DocumentScanUIState.ScanResultState -> ScanResult(state, generateOCRClicked, retryClicked)
        is DocumentScanViewModel.DocumentScanUIState.ScanOCRState -> ScanOCR(state)
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
fun ScanOCR(state: DocumentScanViewModel.DocumentScanUIState.ScanOCRState) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val painter: AsyncImagePainter = when (state.source) {
            is DocumentScanViewModel.OCRSource.UriSource -> {
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.source.uri)
                        .placeholder(R.drawable.image)
                        .build()
                )
            }

            is DocumentScanViewModel.OCRSource.ResSource -> {
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.source.resId)
                        .placeholder(if (LocalInspectionMode.current) R.drawable.the_call else R.drawable.image)
                        .build()
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text("Scanned document resulted in:", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(all = 8.dp))
            Image(
                painter = painter,
                contentDescription = "Scanned Image",
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(10f / 16f)
                    .padding(bottom = 24.dp)
            )
        }

        if(state.scanResults != null) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.scanResults.map { line ->
                    item {
                        Text(
                            text = line,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(all = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScanResult(state: DocumentScanViewModel.DocumentScanUIState.ScanResultState, generateOCRClicked: (DocumentScanViewModel.OCRSource) -> Unit, scanAgainClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.uri)
                    .placeholder(R.drawable.image)
                    .build()
            ),
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

        Button(onClick = { generateOCRClicked(DocumentScanViewModel.OCRSource.ResSource(R.drawable.the_call)) }, modifier = Modifier.padding(top = 12.dp)) {
            Icon(imageVector = Icons.Default.Create, contentDescription = "Scan", modifier = Modifier.padding(end = 8.dp))
            Text(
                text = "Generate OCR"
            )
        }
    }
}

@Preview
@Composable
fun DocumentScanOCRPreview() {
    ScanOCR(
        state = DocumentScanViewModel.DocumentScanUIState.ScanOCRState(DocumentScanViewModel.OCRSource.ResSource(R.drawable.the_call), scanResults = listOf(
            "This is the first line",
            "This is the second line",
            "This is the third line"
        ))
    )
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
        state = DocumentScanViewModel.DocumentScanUIState.ScanResultState(Uri.EMPTY)
    )
}
