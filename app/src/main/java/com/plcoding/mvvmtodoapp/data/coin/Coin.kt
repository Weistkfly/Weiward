package com.plcoding.mvvmtodoapp.data.coin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    val earnedCoins: Int,
    val spentCoins: Int,
    @PrimaryKey val id: Int? = null
)
