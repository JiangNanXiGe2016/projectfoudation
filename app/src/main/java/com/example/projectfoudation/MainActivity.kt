package com.example.projectfoudation

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.projectfoudation.ui.theme.ProjectfoudationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectfoudationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldExample()
                }
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }
    var itemCount by remember {
        mutableIntStateOf(20)
    }
    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text("Message List")
        })
    },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                itemCount++
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ListView(itemCount)
        }
    }
}


@Composable
fun ListView(itemsCount:Int) {
    LazyColumn {
        items(itemsCount) { index ->
            Spacer(modifier = Modifier.height(10.dp))
            MessageCard(MessageCard("yangliang $index", "Hello jetpack compose"))

        }
    }
}

@Composable
fun MessageCard(message: MessageCard) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.mipmap.img),
            contentDescription = "image avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.background(color = Color.White),
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = "this is a message from ${message.author}",
                color = Color.Black,
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
            )
            // Spacer(modifier = Modifier.height(10.dp).background(Color.Red))
            Text(
                text = "this is a message from ${message.content}",
                color = Color.Black,
                modifier = Modifier.background(color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun MessageCardPreview() {
    ProjectfoudationTheme {
        MessageCard(MessageCard("yangliang", "Hello jetpack compose"))
    }
}

data class MessageCard(val author: String, val content: String)