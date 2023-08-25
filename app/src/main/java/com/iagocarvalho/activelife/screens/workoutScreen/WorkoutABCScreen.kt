package com.iagocarvalho.activelife.screens.workoutScreen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.iagocarvalho.activelife.constants.GenericTextFild
import com.iagocarvalho.activelife.constants.SubmitButton
import com.iagocarvalho.activelife.model.modelUsers.ModelExerciceFB
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.CardTreiner
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutABCScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: WorkoutABCScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val getExercisesFromFB = viewModel.getExerciceFb().collectAsState(mutableListOf()).value

    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Seu Treino",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    }, bottomBar = { BottomNavigationScreen(navController = navController) }) { innerpadding ->
        Box(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column() {
                CardTreiner(navController = navController, isHomeScreen = false)
                Divider(thickness = 2.dp)
                LazyColumn {
                    itemsIndexed(getExercisesFromFB) { postion, exerciceFLazy ->
                        Log.d("getExercisesFromFB", "WorkoutABCScreen: $exerciceFLazy")
                        ExerciseFromFBWorkOut(exercices = exerciceFLazy)
                    }
                }
            }
        }

    }
}

@Composable
fun ExerciseFromFBWorkOut(
    exercices: ModelExerciceFB,
    viewModel: WorkoutABCScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val getfunbyViewModel = viewModel

    val context = LocalContext.current

    val expanded = remember { mutableStateOf(false) }

    val repeticoes = remember {
        mutableStateOf("")
    }
    val Series = remember {
        mutableStateOf("")
    }
    val carga = remember {
        mutableStateOf("")
    }

    Card(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            val imageLoader =
//                ImageLoader.Builder(LocalContext.current)
//                    .components {
//                        if (Build.VERSION.SDK_INT >= 28) {
//                            add(ImageDecoderDecoder.Factory())
//                        } else {
//                            add(GifDecoder.Factory())
//                        }
//                    }.build()
//
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(data = exercices.gif_url)
//                    .apply(block = { size(Size.ORIGINAL) })
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "",
//                modifier = Modifier
//                    .height(80.dp)
//                    .width(80.dp),
//                imageLoader = imageLoader
//            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = exercices.name, style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                )
                Text(
                    text = "${exercices.series} Series", style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                )
                Text(
                    text = "${exercices.repeticoes} repetições", style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                )

            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Text(text = "Carga : Kg")
                }

                Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceAround) {
                    Text(text = exercices.cargar)

                }

            }
            Icon(
                modifier = Modifier.clickable { expanded.value = !expanded.value },
                imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = ""

            )

        }
        AnimatedVisibility(visible = expanded.value) {
            Column() {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Diga: Atualize sua carga, repetiçoes e series, acompanhe seu progreço constantemente "

                )
                GenericTextFild(
                    TextFild = repeticoes,
                    keyboardType = KeyboardType.Number,
                    labelId = "Repetiçoes"
                )
                GenericTextFild(
                    TextFild = Series,
                    keyboardType = KeyboardType.Number,
                    labelId = "Series"
                )
                GenericTextFild(
                    TextFild = carga,
                    keyboardType = KeyboardType.Number,
                    labelId = "Cargar: Kg"
                )
                SubmitButton(textId = "Atualizar", loadind = false, validInputs = true) {
                    expanded.value = !expanded.value
                    if (carga.value.isNotEmpty()) {
                        getfunbyViewModel.updateWorkOut(
                            "treinoA",
                            "cargar",
                            exercices.documenteId,
                            carga.value,
                        ).run {
                            Toast.makeText(
                                context,
                                "Treino Atualizado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    if (repeticoes.value.isNotEmpty()) {
                        getfunbyViewModel.updateWorkOut(
                            "treinoA",
                            "repeticoes",
                            exercices.documenteId,
                            repeticoes.value,

                        )
                    }
                    if (Series.value.isNotEmpty()) {
                        getfunbyViewModel.updateWorkOut(
                            "treinoA",
                            "series",
                            exercices.documenteId,
                            Series.value,
                        )
                    }
                }
            }
        }

    }
}