package com.example.pertemuan_enam_parsing_data.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pertemuan_enam_parsing_data.layout.component.FormattedPriceLabel


@Composable
fun LayoutPesanan(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onIncrement: () -> Unit = {},
    onDecrement: () -> Unit = {},
    modifier: Modifier = Modifier,
    jumlahPesanan: Int = 0

) {
    var selectedValue by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Menu", fontSize = 16.sp)
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Jumlah Pesanan", fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = onDecrement, enabled = selectedValue.isNotEmpty()) {
                    Text(text = "-",fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Text(text = "$jumlahPesanan")
                IconButton(onClick = onIncrement, enabled = selectedValue.isNotEmpty()) {
                    Text(text = "+", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

            }
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text("Cancel")
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty() && jumlahPesanan > 0,
                onClick = onNextButtonClicked
            ) {
                Text("Next")
            }
        }
    }

}


