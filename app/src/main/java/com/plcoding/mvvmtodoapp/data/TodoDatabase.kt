package com.plcoding.mvvmtodoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.data.coin.CoinDao
import com.plcoding.mvvmtodoapp.data.todo.Todo
import com.plcoding.mvvmtodoapp.data.todo.TodoDao

@Database(
    entities = [Todo::class,
               Coin::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
    abstract val daoCoin: CoinDao
}


