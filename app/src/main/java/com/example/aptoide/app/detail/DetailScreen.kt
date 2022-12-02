package com.example.aptoide.app.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.aptoide.app.MainViewModel

@Composable
fun DetailScreen(viewModel: MainViewModel, navController: NavController) {

    var offset = rememberScrollState()

    Surface (modifier = Modifier.verticalScroll(offset),
        color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = viewModel.app.graphic),
                        modifier = Modifier.size(
                            width = maxOf(Dp.Infinity),
                            height = maxOf(220.dp)
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Article Image", alignment = Alignment.Center
                    )
                }
                Card(modifier = Modifier
                    .width(120.dp)
                    .height(250.dp)
                    .padding(top = 150.dp, start = 20.dp), shape = RoundedCornerShape(30.dp),
                border = BorderStroke(2.dp, Color.White)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = viewModel.app.icon),
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Article Image", alignment = Alignment.Center
                    )
                }

                Card(modifier = Modifier
                    .size(65.dp)
                    .padding(10.dp), shape = RoundedCornerShape(30.dp)) {
                    IconButton(onClick = { navController.navigate("main"){popUpTo("main")} }, Modifier.padding(10.dp)) {
                        Icon(
                            Icons.Rounded.ArrowBack, contentDescription = "back button",
                            Modifier.size(35.dp)
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = viewModel.app.name!!, fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold, maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                Text(text = "★ ${viewModel.app.rating}", fontSize = 19.sp,
                    fontWeight = FontWeight.Medium, maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                Text(text = "Downloads ${viewModel.app.downloads}", fontSize = 19.sp,
                    fontWeight = FontWeight.Medium, maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                Text(text = "Last updated: ${viewModel.app.updated}", fontSize = 19.sp,
                    fontWeight = FontWeight.Medium, maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Download HERE")
                }
            }
        }
    }
}