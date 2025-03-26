package com.example.roverphotos

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity
import com.example.roverphotos.navigation.RoverNavigation
import com.example.roverphotos.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                RoverNavigation()
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

@Preview(showBackground = true)
@Composable
fun preview() {
    MyApp {
        RoverNavigation()
    }
}