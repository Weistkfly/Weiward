package com.plcoding.mvvmtodoapp.data.todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.mvvmtodoapp.ui.theme.*

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    val isImportant: Boolean,
    val taskColor: Int,
    val dateDone: Long,
    val coinValue: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val taskColors = listOf(androidTask, selfTask, commandedTask, schoolTask)
    }
}

