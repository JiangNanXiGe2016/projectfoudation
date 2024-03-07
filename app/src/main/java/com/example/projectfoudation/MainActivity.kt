package com.example.projectfoudation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectfoudation.ui.theme.Pink40
import com.example.projectfoudation.ui.theme.ProjectfoudationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectfoudationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ScaffoldExample()
                }
            }
        }
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }
    var itemCount by remember {
        mutableIntStateOf(20)
    }
    val context = LocalContext.current
    val activity = context as Activity
    var selectedItem by remember { mutableIntStateOf(0) }
    val tabs = listOf("main", "favorite", "set")
    val navController = rememberNavController()

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.background(Pink40), title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                //  verticalAlignment =  Alignment.CenterVertically
            ) {
                Text(tabs[selectedItem])
            }
        }, navigationIcon = {
            IconButton(onClick = { activity.finish() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        }, actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
            }

        })
    }, bottomBar = {
        BottomNavigation {
            tabs.forEachIndexed { index, item ->
                BottomNavigationItem(modifier = Modifier.background(Pink40), icon = {
                    when (index) {
                        0 -> Icon(Icons.Filled.Home, contentDescription = null)
                        1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                        2 -> Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                }, label = { Text(item) }, selected = selectedItem == index, onClick = {
                    selectedItem = index
                    navController.navigate(tabs[index])
                })
            }
        }
    },

        floatingActionButton = {
            FloatingActionButton( onClick = {
                itemCount++
                showToast(context, "count:$itemCount")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            NavHost(navController = navController, startDestination = tabs[selectedItem]) {
                composable(tabs[0]) {
                    //首页Compose
                    MainScreen(itemsCount = itemCount)
                }
                composable(tabs[1]) {
                    //咨询Compose

                }
                composable(tabs[2]) {
                    //消息Compose

                }
            }

        }
    }
}


data class MessageCard(val author: String, val content: String)