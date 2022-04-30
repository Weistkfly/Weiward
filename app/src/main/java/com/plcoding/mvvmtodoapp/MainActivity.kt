package com.plcoding.mvvmtodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.mvvmtodoapp.ui.add_edit_todo.AddEditTodoScreen
import com.plcoding.mvvmtodoapp.ui.bottom_navigation_bar.BottomNavItem
import com.plcoding.mvvmtodoapp.ui.bottom_navigation_bar.BottomNavigationBar
import com.plcoding.mvvmtodoapp.ui.done_todo_list.DoneTodoListScreen
import com.plcoding.mvvmtodoapp.ui.shop.ShopScreen
import com.plcoding.mvvmtodoapp.ui.theme.MVVMTodoAppTheme
import com.plcoding.mvvmtodoapp.ui.todo_list.TodoListScreen
import com.plcoding.mvvmtodoapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTodoAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Tasks",
                                    route = Routes.TODO_LIST,
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Done",
                                    route = Routes.DONE_TODO_LIST,
                                    icon = Icons.Default.Notifications
                                ),
                                BottomNavItem(
                                    name = "Shop",
                                    route = Routes.SHOP,
                                    icon = Icons.Default.Settings
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.TODO_LIST
                        ) {
                            composable(Routes.TODO_LIST) {
                                TodoListScreen(
                                    onNavigate = {
                                        navController.navigate(it.route)
                                    }
                                )
                            }
                            composable(Routes.DONE_TODO_LIST) {
                                DoneTodoListScreen(

                                )
                            }
                            composable(Routes.SHOP) {
                                ShopScreen()
                            }
                            composable(
                                route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                                arguments = listOf(
                                    navArgument(name = "todoId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddEditTodoScreen(
                                    onPopBackStack = {
                                        navController.popBackStack()
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}
