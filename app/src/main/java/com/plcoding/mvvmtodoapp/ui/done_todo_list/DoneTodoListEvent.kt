package com.plcoding.mvvmtodoapp.ui.done_todo_list

import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.data.todo.Todo

sealed class DoneTodoListEvent {
    data class OnDeleteTodoClick(val todo: Todo): DoneTodoListEvent()
    //data class ClaimCoinReward(val todo: Todo, val coin: Coin?): DoneTodoListEvent()
}
