package com.plcoding.mvvmtodoapp.ui.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.data.coin.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel(){

    var coin by mutableStateOf<Coin?>(null)
        private set

    var availableCoin by mutableStateOf(0)
        private set

    var availableTime by mutableStateOf(0)
        private set

    init {
            viewModelScope.launch {
                repository.getCoinById(1)?.let { coin ->
                    availableCoin = coin.earnedCoins - coin.spentCoins
                    availableTime = coin.timeInMinutes
                    this@ShopListViewModel.coin = coin
                }
            }
    }

    fun onEvent(event: ShopListEvent){
        when(event){
            is ShopListEvent.BuyTime -> {
                viewModelScope.launch {
                    availableCoin = coin?.earnedCoins!! - coin?.spentCoins!!
                    repository.insertCoin(
                        event.coin.copy(
                            spentCoins = event.coin.spentCoins + event.coinSpent,
                            timeInMinutes = event.boughtTime
                        )
                    )
                }
            }
        }
    }
}