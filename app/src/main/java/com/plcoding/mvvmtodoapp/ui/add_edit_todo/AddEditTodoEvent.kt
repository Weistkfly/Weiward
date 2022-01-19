package com.plcoding.mvvmtodoapp.ui.add_edit_todo

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String): AddEditTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditTodoEvent()
    data class OnTaskTypeChange(val color: Int): AddEditTodoEvent()
    object OnSaveTodoClick: AddEditTodoEvent()
}
