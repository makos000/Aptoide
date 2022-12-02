package com.example.aptoide.app.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.aptoide.app.MainViewModel
import com.example.aptoide.app.ui.theme.CustomColors
import com.example.aptoide.domain.model.Parameters

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {
    LaunchedEffect(key1 = true ){
        //calling viewmodel function to fetch data
        viewModel.getData()
    }

    //list collecting data from flow in viewModel as a state (to recompose when data changes)
    val list = viewModel.data.collectAsState().value.data?.get(0)?.appModel

    //list to sort data by rating
    var listByRating: List<Parameters>

    if (list != null){
        if (list.isNotEmpty()){
            //actual sort happening here
            listByRating = list.sortedByDescending { it.rating }
            Column {

                //Row with Editors choice and "MORE" text

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp, start = 10.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(text = "Editors Choice",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp)
                    Text(text = "MORE", textAlign = TextAlign.End, modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp).align(Bottom), color = CustomColors.Orange200)
                }

                //LazyRow component functioning like a horizontal recyclerView in XML based projects
                //Displays elements in list

                LazyRow{
                    itemsIndexed(items = list){index, item ->
                        Card(onClick = {navController.navigate("detail"){popUpTo("main")}
                            viewModel.app = item},
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = 8.dp) {
                            Box(contentAlignment = Alignment.BottomStart) {
                                Image(painter = rememberAsyncImagePainter(model = item.graphic),
                                    contentDescription = "App graphic Image",
                                    modifier = Modifier.size(width = 350.dp, height = 220.dp).drawWithCache {
                                        val gradient = Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black),
                                            startY = size.height-200,
                                            endY = size.height
                                        )
                                        onDrawWithContent {
                                            drawContent()
                                            drawRect(gradient,blendMode = BlendMode.Multiply)
                                        }},
                                    contentScale = ContentScale.Crop,)
                                Column(modifier = Modifier.padding(10.dp).width(300.dp)) {
                                    Text(text = item.name!!, color = Color.White,
                                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis
                                    )
                                    Text(text = "★ ${item.rating}", color = Color.White)
                                }
                            }
                        }
                    }
                }


                //Row with Top apps and "MORE" text

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp, start = 10.dp)) {
                    Text(text = "Top apps",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp)
                    Text(text = "MORE", textAlign = TextAlign.End, modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp).align(Bottom), color = CustomColors.Orange200)
                }

                //LazyRow component functioning like a horizontal recyclerView in XML based projects
                //Displays elements in listByRating

                LazyRow(modifier = Modifier.padding(5.dp)){
                    itemsIndexed(items = listByRating){index, item ->
                        Card(onClick = {navController.navigate("detail"){popUpTo("detail")}
                                       viewModel.app = item},
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            elevation = 8.dp) {
                            Column(modifier = Modifier
                                .width(110.dp)
                                .height(180.dp)) {
                                Card(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Image(painter = rememberAsyncImagePainter(model = item.graphic),
                                        contentDescription = "App Icon Image",
                                        modifier = Modifier.size(width = 90.dp, height = 90.dp),
                                        contentScale = ContentScale.Crop)
                                }
                                Column(modifier = Modifier.padding(horizontal = 10.dp).height(40.dp)) {
                                    Text(text = item.name!!, maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.width(150.dp), fontSize = 15.sp)

                                }
                                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                                    Text(text = "★ ${item.rating}", fontSize = 15.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}