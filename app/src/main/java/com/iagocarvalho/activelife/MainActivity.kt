package com.iagocarvalho.activelife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.iagocarvalho.activelife.navigation.ReaderNagivation
import com.iagocarvalho.activelife.ui.theme.ActiveLifeTheme
import java.util.Collections

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActiveLifeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    ReaderNagivation()
                }
            }
        }
        MobileAds.initialize( this ){}
      //  Opcional se você deseja adicionar dispositivo de teste
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("866051064946433"))
            .build()
        MobileAds.setRequestConfiguration(configuration)

    }
}

