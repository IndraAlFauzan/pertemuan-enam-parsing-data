package com.example.pertemuan_enam_parsing_data.layout

import androidx.lifecycle.ViewModel
import com.example.pertemuan_enam_parsing_data.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setKuantitas(jumlahkuantitas: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                kuantitas = jumlahkuantitas,
                totalHarga = hitungTotal(jumlahkuantitas)
            )
        }
    }

    fun setPesanan(namaPesanan: String) {
        _uiState.update { currentState ->
            currentState.copy(
                pesanan = namaPesanan
            )
        }
    }

    fun setJenisPesanan(jenisPesanan: String) {
        _uiState.update { currentState ->
            currentState.copy(
                jenisPesanan = jenisPesanan
            )
        }
    }

    // Calculate total price
    fun hitungTotal(kuantitas: Int = _uiState.value.kuantitas): String {
        val hargaPerItem = 10000.0
        val total = hargaPerItem * kuantitas
        val formattedPrice = "Rp." + String.format("%,.0f", total)
        return formattedPrice

    }

    // Reset order state to default
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

}