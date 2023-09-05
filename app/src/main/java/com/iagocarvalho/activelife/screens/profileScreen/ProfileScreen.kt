package com.iagocarvalho.activelife.screens.profileScreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.iagocarvalho.activelife.constants.BannerAdView
import com.iagocarvalho.activelife.screens.homeScreen.BottomNavigationScreen
import com.iagocarvalho.activelife.screens.homeScreen.TopAppBarScren

private var mInterstitialAd: InterstitialAd? = null
// ca-app-pub-1389782159432301/8756354855  <- real
// ca-app-pub-3940256099942544/1033173712 <- test
private val adId  = "ca-app-pub-1389782159432301/8756354855"
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
        TopAppBarScren(
            navController = navController,
            PageTitle = "Profile",
            isHomeScreen = false
        ) {
            navController.popBackStack()
        }
    },
        bottomBar = { BottomNavigationScreen(navController = navController) }
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                BannerAdView()
                ShowProfile()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowProfile(
    navController: NavController = NavController(LocalContext.current),
    viewModel: ProfileScreenViewModel = viewModel()
) {
    val contex = LocalContext.current
    val expande = remember { mutableStateOf(false) }
    val adStatus = remember { mutableStateOf(false) }
    val getDataUserInfo = viewModel.state.value
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

    fun loadinInterestitailAd(adStatus: (Boolean) -> Unit){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(contex, adId, adRequest, object : InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                mInterstitialAd = null
                Log.d("Ad_Stut", "onAdFailedToLoad: $error")
                adStatus.invoke(false)
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                mInterstitialAd = interstitialAd
                Log.i("Ad_Stut", "onAdFload: ")
                adStatus.invoke(true)
            }
        })

    }

    fun showInterticialAd(){
        mInterstitialAd?.let {ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    Log.i("Ad_Stut", "onAdDismissedFullScreenContent: ")
                    mInterstitialAd = null
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    Log.i("Ad_Stut", "onAdImpression: ")
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    Log.i("Ad_Stut", "onAdClicked: ")
                }
            }
            ad.show(contex as Activity)
        }?: kotlin.run {
            Toast.makeText(contex, "ad is null", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
        Text(
            text = getDataUserInfo.display_name,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { },
            style = styleNumbers
        )
        Column {
            Card(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "${getDataUserInfo.peso}kg", style = styleNumbers)
                        Text(text = "Peso", style = styleString)
                    }
                    Column {
                        Text(text = "${getDataUserInfo.altura}cm", style = styleNumbers)
                        Text(text = "altura", style = styleString)
                    }
                    Column {
                        Text(text = getDataUserInfo.idade, style = styleNumbers)
                        Text(text = "idade", style = styleString)
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ElevatedButton(onClick = {
                            if (adStatus.value){
                                showInterticialAd()
                                adStatus.value = false
                            }else{
                                loadinInterestitailAd {
                                    adStatus.value = it
                                }
                            }
                        }) {
                            Text(text = "Apoie é Gratis ^^")

                        }
                        Icon(imageVector = if (expande.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                expande.value = !expande.value
                            })
                    }
                    if (expande.value) {

                        Text(
                            text = "Ajude a tornar Active Life uma realidade!\n" +
                                    "\n" +
                                    "Active Life é um aplicativo que ajudará pessoas a montar seus treinos e contrubuir para uma vida ativa." +
                                    " É um projeto inovador que tem o potencial.\n" +
                                    "\n" +
                                    "Sua contribuição será fundamental para o sucesso de Active Life. Com seu apoio, poderemos Desenvolver e Aprimorar o Aplicativo e continuar o disponibilizando de forma gratuita.\n" +
                                    "\n" +
                                    "Ajude a fazer a diferença!",
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }



}
