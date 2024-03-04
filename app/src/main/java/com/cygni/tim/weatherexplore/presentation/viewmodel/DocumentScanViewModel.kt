package com.cygni.tim.weatherexplore.presentation.viewmodel

import android.content.IntentSender
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.cygni.tim.weatherexplore.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DocumentScanViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<DocumentScanUIState>(DocumentScanUIState.ReadyToScanState(true))
    val uiState: StateFlow<DocumentScanUIState> = _uiState

    fun onScanClicked() {
        initiateScan()
    }

    fun onRetryClicked() {
        _uiState.value = DocumentScanUIState.ReadyToScanState(true)
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
            _uiState.value = DocumentScanUIState.ScanResult(uri)
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
        data class ScanResult(val uri: Uri) : DocumentScanUIState()
    }
}
