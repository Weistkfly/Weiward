package com.plcoding.mvvmtodoapp.ui.todo_list

import com.plcoding.mvvmtodoapp.data.todo.Todo

sealed class TodoListEvent {
    data class OnDeleteTodoClick(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    data class OnTodoClick(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
    data class OnTaskImportanceChange(val todo: Todo, val isImportant: Boolean): TodoListEvent()
}
