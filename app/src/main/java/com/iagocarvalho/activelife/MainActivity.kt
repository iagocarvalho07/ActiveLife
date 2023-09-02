package com.iagocarvalho.activelife

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.iagocarvalho.activelife.navigation.ReaderNagivation
import com.iagocarvalho.activelife.ui.theme.ActiveLifeTheme
import java.util.Collections

class MainActivity : ComponentActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private val adId  = "ca-app-pub-3940256099942544/1033173712"
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

    fun loadinInterestitailAd(adStatus: (Boolean) -> Unit){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, adId, adRequest, object : InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                mInterstitialAd = null
                Log.d("Ad_Stut", "onAdFailedToLoad: $error")
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                mInterstitialAd = interstitialAd
                Log.i("Ad_Stut", "onAdFload: ")
            }
        })

    }

    private fun showInterticialAd(){
        mInterstitialAd?.let {ad ->
            ad.fullScreenContentCallback = object :FullScreenContentCallback(){
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
            ad.show(this)
        }?: kotlin.run {
            Toast.makeText(this, "ad is null", Toast.LENGTH_SHORT).show()
        }
    }
}

