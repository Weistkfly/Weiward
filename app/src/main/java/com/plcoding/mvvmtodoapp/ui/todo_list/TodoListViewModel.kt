package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mvvmtodoapp.data.todo.Todo
import com.plcoding.mvvmtodoapp.data.TodoRepository
import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.util.Routes
import com.plcoding.mvvmtodoapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()
    val coins = repository.getCoins()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when(event) {
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                viewModelScope.launch {
                    if (coins.firstOrNull().isNullOrEmpty()){
                        repository.insertCoin(
                            Coin(
                                earnedCoins = 0,
                                spentCoins = 0,
                                timeInMinutes = 0,
                                id = 1
                            )
                        )
                    }
                }
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is TodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
            is TodoListEvent.OnTaskImportanceChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isImportant = !event.isImportant,
                            coinValue = if (!event.isImportant){
                                event.todo.coinValue + 2
                            } else {
                                event.todo.coinValue - 2
                            }
                        )
                    )
                }
            }
            is TodoListEvent.ClaimCoinReward -> {
                viewModelScope.launch {
                        repository.insertCoin(
                            event.coin.copy(
                                earnedCoins = event.todo.coinValue + event.coin.earnedCoins
                            )
                        )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

//When I click on save or Add I need to verify whether there is a Coin Object in the database