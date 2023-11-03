package com.example.pertemuan_enam_parsing_data.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pertemuan_enam_parsing_data.data.DummyData

@Composable
fun LayoutHome(onNextButtonClicked: (String) -> Unit) {

    val data = DummyData.jenisPesanan

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = "Selamat Datang di Warung Makan Sederhana")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Silahkan Pilih Jenis Pesanan")
        Spacer(modifier = Modifier.padding(16.dp))
        data.forEach { item ->
            ElevatedButton(onClick = { onNextButtonClicked(item) }) {
                Text(text = item)
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }


    }


}



