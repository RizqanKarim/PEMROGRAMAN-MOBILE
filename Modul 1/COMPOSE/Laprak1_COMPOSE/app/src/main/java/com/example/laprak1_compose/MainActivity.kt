package com.example.laprak1_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.laprak1_compose.ui.theme.Laprak1_XMLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laprak1_XMLTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    var result1 by remember { mutableIntStateOf(0) }
    var result2 by remember { mutableIntStateOf(0) }

    var showMessage by remember { mutableStateOf(false) }
    var messageText by remember { mutableStateOf("") }

    val imageResource1 = when (result1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    val imageResource2 = when (result2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    LaunchedEffect(showMessage, result1, result2) {
        if (showMessage) {
            kotlinx.coroutines.delay(2000)
            showMessage = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResource1),
                    contentDescription = "Dadu 1",
                    modifier = Modifier.size(120.dp)
                )
                Image(
                    painter = painterResource(id = imageResource2),
                    contentDescription = "Dadu 2",
                    modifier = Modifier.size(120.dp)
                )
            }

            Button(onClick = {
                result1 = (1..6).random()
                result2 = (1..6).random()

                if (result1 == result2) {
                    messageText = "Selamat, Anda dapat dadu double"
                } else {
                    messageText = "Anda belum beruntung!"
                }
                showMessage = true
            }) {
                Text(text = "Roll")
            }
        }

        if (showMessage) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF4E5F7))
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = messageText,
                    color = Color.Black
                )
            }
        }
    }
}