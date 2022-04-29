package com.plcoding.mvvmtodoapp.data

import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.data.todo.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getTodos(): Flow<List<Todo>>

    suspend fun getCoinById(id: Int): Coin?

    suspend fun insertCoin(coin: Coin)

    fun getCoins(): Flow<List<Coin>>
}