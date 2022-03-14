package com.plcoding.mvvmtodoapp.ui.done_todo_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoItem
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListEvent
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListViewModel
import com.plcoding.mvvmtodoapp.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun DoneTodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ){
            Text(
                text = "Done",
                style = MaterialTheme.typography.h4
            )
            LazyColumn()
            {
                items(todos.value) { todo ->
                    if (todo.isDone) {
                        TodoItem(
                            todo = todo,
                            onEvent = viewModel::onEvent,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onEvent(TodoListEvent.OnTodoClick(todo))
                                }
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
