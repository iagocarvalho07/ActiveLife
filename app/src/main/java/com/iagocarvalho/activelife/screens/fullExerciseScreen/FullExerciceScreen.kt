package com.iagocarvalho.activelife.screens.fullExerciseScreen

import android.graphics.drawable.Icon
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.iagocarvalho.activelife.R
import com.iagocarvalho.activelife.constants.BannerAdView
import com.iagocarvalho.activelife.constants.SearchBar
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FullExerciceScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: FullExerciceScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),

    ) {
    val bodyPart = remember {
        mutableStateOf("")
    }
    val exerciceName = remember {
        mutableStateOf("")
    }
    val viewModels = viewModel.repositorys.getExercicesFromRoom().collectAsState(listOf()).value
    val filtredByExerciceName = viewModels.filter { it.name.contains(exerciceName.value) }
    val filtredByBodyPart = viewModels.filter { it.target.contains(bodyPart.value) }

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
                BannerAdView()
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    colors = CardDefaults.cardColors(Color(0xFFE47C0E))
                ) {
                    val listBodyPart = listOf(
                        "abductors",
                        "abs",
                        "adductors",
                        "biceps",
                        "calves",
                        "cardiovascular system",
                        "delts",
                        "forearms",
                        "glutes",
                        "hamstrings",
                        "lats",
                        "levator scapulae",
                        "pectorals",
                        "quads",
                        "serratus anterior",
                        "spine",
                        "traps",
                        "triceps",
                        "upper back"
                    )
                    LazyRow(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                        itemsIndexed(listBodyPart) { postion, itens ->
                            Button(
                                onClick = { bodyPart.value = itens },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFE47C0E
                                    )
                                )
                            ) {
                                Text(text = itens)
                            }
                        }
                    }
                    SearchBar(exerciceName)
                }
                if (bodyPart.value.isEmpty() && exerciceName.value.isEmpty()) {
                    LazyColumn {
                        items(viewModels) { allexercises ->
                            CarViewExercisesByRoom(
                                bodyPart = allexercises.bodyPart,
                                equipment = allexercises.equipment,
                                gifUrl = allexercises.gifUrl,
                                id = allexercises.id,
                                name = allexercises.name,
                                target = allexercises.target
                            )

                        }
                    }
                } else if (bodyPart.value.isNotEmpty() && exerciceName.value.isEmpty()) {
                    if (exerciceName.value.isNotEmpty()) {
                        exerciceName.value = ""
                    }
                    LazyColumn {
                        items(filtredByBodyPart) { allexercises ->
                            CarViewExercisesByRoom(
                                bodyPart = allexercises.bodyPart,
                                equipment = allexercises.equipment,
                                gifUrl = allexercises.gifUrl,
                                id = allexercises.id,
                                name = allexercises.name,
                                target = allexercises.target
                            )

                        }
                    }
                } else if (exerciceName.value.isNotEmpty()) {
                    if (bodyPart.value.isNotEmpty()) {
                        bodyPart.value = ""
                    }
                    LazyColumn {
                        items(filtredByExerciceName) { allexercises ->
                            CarViewExercisesByRoom(
                                bodyPart = allexercises.bodyPart,
                                equipment = allexercises.equipment,
                                gifUrl = allexercises.gifUrl,
                                id = allexercises.id,
                                name = allexercises.name,
                                target = allexercises.target
                            )

                        }
                    }


                }

            }
        }
    }
}

@Composable
fun CarViewExercisesByRoom(
    bodyPart: String,
    equipment: String,
    gifUrl: String,
    id: String,
    name: String,
    target: String,
    viewModel: FullExerciceScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val scope = rememberCoroutineScope()
    val styleString = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight(700),
    )
    val expande = remember { mutableStateOf(false) }

    val treinoA = remember {
        mutableStateOf(false)
    }
    val treinoB = remember {
        mutableStateOf(false)
    }
    val treinoC = remember {
        mutableStateOf(false)
    }
    val treinoD = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier) {
                val openDialog = remember { mutableStateOf(false) }
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
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = gifUrl)
                            .apply(block = {
                                size((Size.ORIGINAL))
                            })
                            .crossfade(true)
                            .build(), imageLoader = imageLoader,
                        contentDescription = "",
                        modifier = Modifier.clickable { openDialog.value = true }
                    ) {
                        val state = painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }


                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openDialog.value = false
                            },
                            title = {
                                Text(text = name)
                            },
                            text = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    val imageLoaders =
                                        ImageLoader.Builder(context = LocalContext.current)
                                            .components {
                                                if (SDK_INT >= 28) {
                                                    add(ImageDecoderDecoder.Factory())
                                                } else {
                                                    add(GifDecoder.Factory())
                                                }
                                            }.build()
                                    SubcomposeAsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(data = gifUrl)
                                            .apply(block = {
                                                size((Size.ORIGINAL))
                                            })
                                            .crossfade(true)
                                            .build(), imageLoader = imageLoaders,
                                        contentDescription = "",
                                        modifier = Modifier.clickable { openDialog.value = true }
                                    ) {
                                        val state = painter.state
                                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                            Column(
                                                modifier = Modifier.fillMaxSize(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        } else {
                                            SubcomposeAsyncImageContent()
                                        }
                                    }
                                }
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        openDialog.value = false
                                    }
                                ) {
                                    Text("Confirm")
                                }
                            },
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = name, style = styleString)
                    Text(text = bodyPart, style = styleString)
                    Text(text = equipment, style = styleString)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expande.value = !expande.value }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Adicionar ao Treino   ->")
                        Icon(imageVector = if (expande.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                expande.value = !expande.value
                            })
                    }
                }
            }
            AnimatedVisibility(
                visible = expande.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RadioButton(
                            selected = treinoA.value,
                            onClick = { treinoA.value = !treinoA.value })
                        Text(text = "A")

                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RadioButton(
                            selected = treinoB.value,
                            onClick = { treinoB.value = !treinoB.value })
                        Text(text = "B")
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RadioButton(
                            selected = treinoC.value,
                            onClick = { treinoC.value = !treinoC.value })
                        Text(text = "C")
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RadioButton(
                            selected = treinoD.value,
                            onClick = { treinoD.value = !treinoD.value })
                        Text(text = "D")
                    }

                    Button(onClick = {
                        if (treinoA.value) {
                            scope.launch(Dispatchers.IO) {
                                viewModel.saveWorkOut(
                                    bodyPart,
                                    equipment,
                                    gifUrl,
                                    id,
                                    name,
                                    target,
                                    "treinoA"
                                )
                            }
                        }
                        if (treinoB.value) {
                            scope.launch(Dispatchers.IO) {
                                viewModel.saveWorkOut(
                                    bodyPart,
                                    equipment,
                                    gifUrl,
                                    id,
                                    name,
                                    target,
                                    "treinoB"
                                )
                            }
                        }
                        if (treinoC.value) {
                            scope.launch(Dispatchers.IO) {
                                viewModel.saveWorkOut(
                                    bodyPart,
                                    equipment,
                                    gifUrl,
                                    id,
                                    name,
                                    target,
                                    "treinoC"
                                )
                            }
                        }
                        if (treinoD.value) {
                            scope.launch(Dispatchers.IO) {
                                viewModel.saveWorkOut(
                                    bodyPart,
                                    equipment,
                                    gifUrl,
                                    id,
                                    name,
                                    target,
                                    "treinoD"
                                )
                            }
                        }
                    }) {
                        Text(text = "Adicionar")

                    }
                }
            }
        }
    }
}