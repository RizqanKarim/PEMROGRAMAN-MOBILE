package com.example.laprak3_compose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CarAppNavigation()
            }
        }
    }
}

@Composable
fun CarAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list_screen") {
        composable("list_screen") { ListScreen(navController) }
        composable("detail_screen/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull()
            val car = carList.find { it.id == carId }
            if (car != null) DetailScreen(car, navController)
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        item {
            Text("Featured Cars", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(carList) { car ->
                    CarCard(car = car, navController = navController, modifier = Modifier.width(300.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("All Cars", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }

        items(carList) { car ->
            CarCard(
                car = car,
                navController = navController,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CarCard(car: Car, navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = car.imageResId),
                contentDescription = car.model,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${car.brand} - ${car.year}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Text(
                    text = "${car.model} | ${car.engine}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(car.webUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Web", fontSize = 12.sp) }

                    Button(
                        onClick = { navController.navigate("detail_screen/${car.id}") },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(0.dp)
                    ) { Text("Detail", fontSize = 12.sp) }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(car: Car, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = car.imageResId),
            contentDescription = car.model,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(250.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${car.brand} ${car.model}", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = "Year: ${car.year}", fontSize = 18.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description:", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Text(text = car.description, fontSize = 16.sp)
        }
    }
}