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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
    val factory = CarViewModelFactory("Premium Sport Cars")
    val viewModel: CarViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = "list_screen") {
        composable("list_screen") {
            ListScreen(navController, viewModel)
        }
        composable("detail_screen/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull()
            val carsList by viewModel.cars.collectAsState()
            val car = carsList.find { it.id == carId }

            car?.let { DetailScreen(it) }
        }
    }
}

@Composable
fun ListScreen(navController: NavController, viewModel: CarViewModel) {
    val listMobil by viewModel.cars.collectAsState()
    val navigateToDetail by viewModel.navigateToDetail.collectAsState()
    val navigateToWeb by viewModel.navigateToWeb.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(navigateToDetail) {
        navigateToDetail?.let { car ->
            navController.navigate("detail_screen/${car.id}")
            viewModel.onNavigationDone()
        }
    }

    LaunchedEffect(navigateToWeb) {
        navigateToWeb?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
            viewModel.onNavigationDone()
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {

        Text(
            text = "Featured Cars",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listMobil) { car ->
                CarItem(
                    car = car,
                    modifier = Modifier.width(320.dp),
                    onWebClick = { viewModel.onWebButtonClicked(car) },
                    onDetailClick = { viewModel.onDetailButtonClicked(car) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "All Cars",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            items(listMobil) { car ->
                CarItem(
                    car = car,
                    modifier = Modifier.fillMaxWidth(),
                    onWebClick = { viewModel.onWebButtonClicked(car) },
                    onDetailClick = { viewModel.onDetailButtonClicked(car) }
                )
            }
        }
    }
}

@Composable
fun CarItem(car: Car, modifier: Modifier = Modifier, onWebClick: () -> Unit, onDetailClick: () -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = car.imageResId),
                contentDescription = car.model,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.height(100.dp).weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${car.brand} - ${car.year}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${car.model} | ${car.engine}",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 18.sp
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = onWebClick,
                        modifier = Modifier.weight(1f).height(32.dp).padding(end = 4.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Web", fontSize = 12.sp)
                    }
                    Button(
                        onClick = onDetailClick,
                        modifier = Modifier.weight(1f).height(32.dp).padding(start = 4.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Detail", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(car: Car) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
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