package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.iagocarvalho.activelife.network.roomDataBaseCache.ExerciceDao
import com.iagocarvalho.activelife.network.roomDataBaseCache.ExerciceRoomRepositoryIMPL
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FullExerciceScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: FullExerciceScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),

    ) {

    val viewModels = viewModel.repositorys.getExercicesFromRoom().collectAsState(listOf()).value

    val styleNumbers = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight(700),
        color = Color(0xFFE47C0E),
    )
    val styleString = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight(700),
    )

    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Full Exercise",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    }, bottomBar = { BottomNavigationScreen(navController = navController) }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(Color(0xFFE47C0E))
                ) {
                    LazyRow {
                        item() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "abductors")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "abs")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "adductors")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "biceps")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "cardiovascular system")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "delts")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "forearms")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "glutes")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "hamstrings")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "levator scapulae")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "pectorals")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "quads")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "serratus anterior")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "traps")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "triceps")
                                }
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text(text = "upper back")
                                }
                            }
                        }
                    }
                }

                if (viewModels == null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn {
                        items(viewModels) { allexercises ->
                            Log.d(
                                "TestApi",
                                "FullExerciceScreen: chamando api  ${allexercises.name}"
                            )
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(modifier = Modifier) {
                                        val imageLoader =
                                            ImageLoader.Builder(context = LocalContext.current)
                                                .components {
                                                    if (SDK_INT >= 28) {
                                                        add(ImageDecoderDecoder.Factory())
                                                    } else {
                                                        add(GifDecoder.Factory())
                                                    }
                                                }.build()
                                        Box(
                                            modifier = Modifier
                                                .width(80.dp)
                                                .height(80.dp)
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data(data = allexercises.gifUrl)
                                                    .apply(block = {
                                                        size(Size.ORIGINAL)
                                                    })
                                                    .crossfade(true)
                                                    .build(), imageLoader = imageLoader,
                                                contentDescription = ""
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.padding(start = 16.dp),
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(text = allexercises.name, style = styleString)
                                            Text(text = allexercises.bodyPart, style = styleString)
                                            Text(text = allexercises.equipment, style = styleString)
                                        }

                                    }
                                }
                            }
                        }
                    }
                              }
            }
        }
    }
}