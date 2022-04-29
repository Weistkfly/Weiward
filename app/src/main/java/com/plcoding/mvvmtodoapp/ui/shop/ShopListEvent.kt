package com.plcoding.mvvmtodoapp.ui.shop

import com.plcoding.mvvmtodoapp.data.coin.Coin

sealed class ShopListEvent {
    data class buyTime(val coin: Coin): ShopListEvent()
}

