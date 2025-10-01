package com.example.lottietest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*

class MainActivity : ComponentActivity() {

    private val appStartTime = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.Url(
                    "https://raw.githubusercontent.com/piyush-kumar-zebpay/News/refs/heads/master/circle_to_square_lottie.json"
                )
            )

            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            var firstFrameLogged by remember { mutableStateOf(false) }

            // Log first frame rendered
            LaunchedEffect(progress) {
                if (!firstFrameLogged && progress > 0f) {
                    val firstFrameTime = System.currentTimeMillis() - appStartTime
                    Log.d("LottiePerf", "Time to first Lottie frame: ${firstFrameTime}ms")
                    firstFrameLogged = true
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = progress
                )
            }
        }
    }
}
