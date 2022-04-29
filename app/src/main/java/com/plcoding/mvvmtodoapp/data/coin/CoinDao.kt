package com.plcoding.mvvmtodoapp.data.coin

import androidx.room.*
import com.plcoding.mvvmtodoapp.data.todo.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: Coin)

    @Query("SELECT * FROM coin WHERE id = :id")
    suspend fun getCoinById(id: Int): Coin?

    @Query("SELECT * FROM coin")
    fun getCoins(): Flow<List<Coin>>

}