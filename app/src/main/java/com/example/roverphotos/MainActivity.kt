package com.example.roverphotos

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.core.app.ComponentActivity
import com.example.roverphotos.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Navigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MarsPhotosTheme {
        content()
        
    }
}