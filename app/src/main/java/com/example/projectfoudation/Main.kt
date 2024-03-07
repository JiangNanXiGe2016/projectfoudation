package com.example.projectfoudation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectfoudation.ui.theme.ProjectfoudationTheme

@Composable
fun ListView(itemsCount: Int) {
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