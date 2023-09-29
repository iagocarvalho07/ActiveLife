package com.iagocarvalho.activelife.domain.model

import androidx.annotation.DrawableRes
import com.iagocarvalho.activelife.R

sealed class OnbordingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,


    ) {
    object first :
        OnbordingPage(image = R.drawable.greetings, title = "Greetings", description = "are you welcomcemc, em,calka")
    object second :
        OnbordingPage(image = R.drawable.explore, title = "Explore", description = "sdasdasdasd you welcomcemc, em,calka")
    object trid :
        OnbordingPage(image = R.drawable.power, title = "power", description = "are yoasdasdu welcomcemc, em,calka")
}