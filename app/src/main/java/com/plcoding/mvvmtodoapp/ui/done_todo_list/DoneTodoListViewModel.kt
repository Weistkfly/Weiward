package com.plcoding.mvvmtodoapp.ui.done_todo_list

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
class DoneTodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()
    val coins = repository.getCoins()

    var coin by mutableStateOf<Coin?>(null)
        private set

    fun onEvent(event: DoneTodoListEvent) {
        when(event) {
            is DoneTodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    repository.deleteTodo(event.todo)
                }
            }
            is DoneTodoListEvent.ClaimCoinReward -> {
                viewModelScope.launch {
                    if (coin == null){
                        coin = Coin(
                            earnedCoins = 0,
                            spentCoins = 0,
                            timeInMinutes = 0,
                            id = 1)
                        repository.insertCoin(
                            coin!!
                        )
                    } else {
                        repository.insertCoin(
                            event.coin!!.copy(
                                earnedCoins = event.todo.coinValue + event.coin.earnedCoins
                            )
                        )
                    }
                }

            }
        }
    }
}