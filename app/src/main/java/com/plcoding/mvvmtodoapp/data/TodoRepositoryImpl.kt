package com.plcoding.mvvmtodoapp.data

import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.data.coin.CoinDao
import com.plcoding.mvvmtodoapp.data.todo.Todo
import com.plcoding.mvvmtodoapp.data.todo.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao,
    private val daoCoin: CoinDao
): TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun insertCoin(coin: Coin) {
        daoCoin.insertCoin(coin)
    }

    override suspend fun getCoinById(id: Int): Coin? {
        return daoCoin.getCoinById(id)
    }

    override fun getCoins(): Flow<List<Coin>> {
        return daoCoin.getCoins()
    }
}