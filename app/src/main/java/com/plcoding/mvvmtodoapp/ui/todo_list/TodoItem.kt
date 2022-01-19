package com.plcoding.mvvmtodoapp.ui.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.mvvmtodoapp.data.Todo
import com.plcoding.mvvmtodoapp.ui.theme.importantTask


@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(todo.taskColor))
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (todo.isImportant){
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(importantTask)
                )}
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            todo.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it)
            }
        }
        IconButton(onClick = {
            onEvent(TodoListEvent.OnTaskImportanceChange(todo, todo.isImportant))
        }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Importance"
            )
        }
        IconButton(onClick = {
            onEvent(TodoListEvent.OnDeleteTodoClick(todo))
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { isChecked ->
                onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
            }
        )
    }
}