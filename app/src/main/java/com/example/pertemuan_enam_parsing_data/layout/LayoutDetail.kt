package com.example.pertemuan_enam_parsing_data.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pertemuan_enam_parsing_data.data.OrderUiState

@Composable
fun LayoutDetail(
    onCancelButtonClicked: () -> Unit = {},
    onSendButtonClicked: () -> Unit = {},
    orderUiState: OrderUiState
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Jenis Pesanan", fontWeight = FontWeight.Bold)
                Text(
                    text = orderUiState.jenisPesanan,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Text(text = "Nama Pesanan", fontWeight = FontWeight.Bold)
                Text(
                    text = orderUiState.pesanan,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Text(text = "Jumlah Pesanan", fontWeight = FontWeight.Bold)
                Text(
                    text = orderUiState.kuantitas.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Text(text = "Total Harga", fontWeight = FontWeight.Bold)
                Text(
                    text = orderUiState.totalHarga,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row {
            OutlinedButton(onClick = onCancelButtonClicked) {
                Text(text = "Batal")
            }

            Spacer(modifier = Modifier.padding(16.dp))
            ElevatedButton(onClick = onSendButtonClicked) {
                Text(text = "Order")
            }
        }

    }

}
