package com.plcoding.mvvmtodoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

   // @Query("SELECT * FROM todo WHERE isImportant = 1")
   // suspend fun getTodoByImportance(isImportant: Boolean): Todo?

   // @Query("SELECT * FROM todo WHERE isDone = 1")
   // suspend fun getDoneTodo(isDone: Boolean): Todo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

}