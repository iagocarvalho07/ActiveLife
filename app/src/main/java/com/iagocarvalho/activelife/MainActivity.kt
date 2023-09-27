package com.iagocarvalho.activelife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.iagocarvalho.activelife.navigation.RootNagivationGraph
import com.iagocarvalho.activelife.ui.theme.ActiveLifeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActiveLifeTheme {
                val navController = rememberNavController()
                RootNagivationGraph(navController = navController)

            }
        }
        MobileAds.initialize(this) {}
        //  Opcional se você deseja adicionar dispositivo de teste
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("866051064946433"))
            .build()
        MobileAds.setRequestConfiguration(configuration)
    }
}

