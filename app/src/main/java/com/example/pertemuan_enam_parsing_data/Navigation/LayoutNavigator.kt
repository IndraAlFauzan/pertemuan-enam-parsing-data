package com.example.pertemuan_enam_parsing_data.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pertemuan_enam_parsing_data.data.DummyData
import com.example.pertemuan_enam_parsing_data.layout.LayoutDetail
import com.example.pertemuan_enam_parsing_data.layout.LayoutHome
import com.example.pertemuan_enam_parsing_data.layout.LayoutPesanan
import com.example.pertemuan_enam_parsing_data.layout.OrderViewModel


enum class BurjoScreen(val title: String) {
    Start(title = "Start"),
    Pesanan(title = "Pesanan"),
    Summary(title = "Summary")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutNavigator(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = BurjoScreen.valueOf(
        backStackEntry?.destination?.route ?: BurjoScreen.Start.name
    )
    Scaffold(
        topBar = {
            WarungAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen
            )
        }
    ) { paddingValues ->
        val uiStet by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = BurjoScreen.Start.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = BurjoScreen.Start.name) {
                LayoutHome(onNextButtonClicked = { jenisPesanan ->
                    viewModel.setJenisPesanan(jenisPesanan)
                    navController.navigate(BurjoScreen.Pesanan.name)
                })

            }

            composable(route = BurjoScreen.Pesanan.name) {

                if (uiStet.jenisPesanan == "Makanan") {
                    LayoutPesanan(
                        subtotal = uiStet.totalHarga,
                        options = DummyData.dataMakanan,
                        onSelectionChanged = { namaPesanan ->
                            viewModel.setPesanan(namaPesanan)
                        },
                        onCancelButtonClicked = {
                            cancelOrderAndNavigateToStart(viewModel, navController)

                        },
                        onNextButtonClicked = {
                            navController.navigate(BurjoScreen.Summary.name)
                        },
                        onIncrement = {
                            viewModel.setKuantitas(uiStet.kuantitas + 1)
                        },
                        jumlahPesanan = uiStet.kuantitas,
                        onDecrement = {
                            viewModel.setKuantitas(uiStet.kuantitas - 1)
                            if (uiStet.kuantitas == 0) {
                                viewModel.setKuantitas(0)
                            }
                        },

                        )
                } else {
                    LayoutPesanan(
                        subtotal = uiStet.totalHarga,
                        options = DummyData.dataMinuman,
                        onSelectionChanged = { namaPesanan ->
                            viewModel.setPesanan(namaPesanan)
                        },
                        onCancelButtonClicked = {
                            cancelOrderAndNavigateToStart(viewModel, navController)

                        },
                        onNextButtonClicked = {
                            navController.navigate(BurjoScreen.Summary.name)
                        },
                        onIncrement = {

                            viewModel.setKuantitas(uiStet.kuantitas + 1)
                        },
                        jumlahPesanan = uiStet.kuantitas,
                        onDecrement = {
                            viewModel.setKuantitas(uiStet.kuantitas - 1)
                            if (uiStet.kuantitas == 0) {
                                viewModel.setKuantitas(0)
                            }
                        },

                    )
                }

            }

            composable(route = BurjoScreen.Summary.name) {
                LayoutDetail(
                    orderUiState = uiStet,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)

                    },
                    onSendButtonClicked = {},
                )

            }

        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarungAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    currentScreen: BurjoScreen,
) {
    TopAppBar(
        title = {
            Text(
                text = (currentScreen.title),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
    )
}


private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(BurjoScreen.Start.name, inclusive = false)
}