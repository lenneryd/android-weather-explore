package com.cygni.tim.weatherexplore.presentation.viewmodel

import android.content.IntentSender
import android.net.Uri
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import com.cygni.tim.weatherexplore.presentation.WeatherExploreApplication
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DocumentScanViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<DocumentScanUIState>(DocumentScanUIState.ReadyToScanState(true))
    val uiState: StateFlow<DocumentScanUIState> = _uiState

    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


    fun onScanClicked() {
        initiateScan()
    }

    fun onRetryClicked() {
        _uiState.value = DocumentScanUIState.ReadyToScanState(true)
    }

    fun onGenerateOCRClicked(source: OCRSource) {
        val inputImage = when (source) {
            is OCRSource.ResSource -> {
                _uiState.value = DocumentScanUIState.ScanOCRState(source)
                InputImage.fromBitmap(ResourcesCompat.getDrawable(WeatherExploreApplication.instance.resources, source.resId, null)!!.toBitmap(), 0)
            }

            is OCRSource.UriSource -> {
                _uiState.value = DocumentScanUIState.ScanOCRState(OCRSource.UriSource(source.uri))
                InputImage.fromFilePath(WeatherExploreApplication.instance, source.uri)
            }
        }

        recognizer.process(inputImage)
            .addOnSuccessListener { visionText ->
                val result = visionText.textBlocks.map { block ->
                    block.lines.fold(StringBuilder()) { acc, line ->
                        if(acc.endsWith("-")) {
                            acc.deleteCharAt(acc.length - 1)
                            acc.append(line.text)
                        } else {
                            acc.append(" " + line.text)
                        }
                        acc
                    }.toString()
                }
                _uiState.value = DocumentScanUIState.ScanOCRState(source, result)
            }
            .addOnFailureListener { e ->
                _uiState.value = DocumentScanUIState.ScanningErrorState(e)
            }
    }

    fun onScanIntent(intentSender: IntentSender) {
        _uiState.value = DocumentScanUIState.ScanWithIntent(intentSender)
    }

    private fun initiateScan() {
        _uiState.value = DocumentScanUIState.StartScanningState
    }

    fun onScanResult(uri: Uri?) {
        if (uri != null) {
            Log.d(DocumentScanViewModel::class.java.simpleName, "Received Scan Result Uri: $uri")
            _uiState.value = DocumentScanUIState.ScanResultState(uri)
        } else {
            Log.w(DocumentScanViewModel::class.java.simpleName, "Did not receive Scan Result URI")
            _uiState.value = DocumentScanUIState.ScanningErrorState(Exception("Failed to scan"))
        }
    }

    fun onScanFailed(error: Exception) {
        error.printStackTrace()
        _uiState.value = DocumentScanUIState.ScanningErrorState(error)
    }

    sealed class DocumentScanUIState {
        data class ReadyToScanState(val isReady: Boolean) : DocumentScanUIState()
        data object StartScanningState : DocumentScanUIState()
        data class ScanWithIntent(val intent: IntentSender) : DocumentScanUIState()
        data class ScanningErrorState(val error: Exception) : DocumentScanUIState()
        data class ScanResultState(val uri: Uri) : DocumentScanUIState()
        data class ScanOCRState(val source: OCRSource, val scanResults: List<String>? = null) : DocumentScanUIState()
    }

    sealed class OCRSource {
        data class UriSource(val uri: Uri) : OCRSource()
        data class ResSource(val resId: Int) : OCRSource()
    }
}
