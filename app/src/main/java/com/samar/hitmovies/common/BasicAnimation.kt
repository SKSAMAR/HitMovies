package com.samar.hitmovies.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samar.hitmovies.R

@Composable
fun BasicAnimation(
    modifier: Modifier = Modifier,
    animation: Int = R.raw.loading
){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    LottieAnimation(
        composition,
        modifier = modifier,
        contentScale = ContentScale.Inside,
        restartOnPlay = true,
        iterations = LottieConstants.IterateForever
    )
}