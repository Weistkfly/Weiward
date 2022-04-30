package com.plcoding.mvvmtodoapp.ui.shop

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ShopScreen(
    viewModel: ShopListViewModel = hiltViewModel()
) {
    val coins = viewModel.coin
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "You have ${viewModel.availableCoin} coins")
            Text(text = "You have ${viewModel.availableTime} minutes to spent")
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { viewModel.onEvent(ShopListEvent.BuyTime(coins!!, 5, 5)) }) {
                Text(text = "Click to spent 5 coins")
            }
        }
    }
}
