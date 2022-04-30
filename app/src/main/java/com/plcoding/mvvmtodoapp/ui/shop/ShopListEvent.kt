package com.plcoding.mvvmtodoapp.ui.shop

import com.plcoding.mvvmtodoapp.data.coin.Coin

sealed class ShopListEvent {
    data class BuyTime(val coin: Coin, val coinSpent: Int, val boughtTime: Int): ShopListEvent()
}

