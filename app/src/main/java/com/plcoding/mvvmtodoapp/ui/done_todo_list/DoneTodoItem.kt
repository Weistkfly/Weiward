package com.plcoding.mvvmtodoapp.ui.done_todo_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.mvvmtodoapp.data.coin.Coin
import com.plcoding.mvvmtodoapp.data.todo.Todo
import com.plcoding.mvvmtodoapp.ui.theme.importantTask
import java.util.*


@Composable
fun DoneTodoItem(
    todo: Todo,
    coin: Coin?,
    onEvent: (DoneTodoListEvent) -> Unit,
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reward: $${todo.coinValue} coins",
                    color = Color.Green
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = com.plcoding.mvvmtodoapp.R.drawable.ic_w_coin_logo),
                    contentDescription = "Coin logo"
                )
                Text(text = "Added on ${Date(todo.dateDone).toInstant()}")
            }
        }
        Button(
            onClick = {
                onEvent(DoneTodoListEvent.ClaimCoinReward(todo, coin))
            }
        ) {
            Text(text = "Claim")
        }
    }
}