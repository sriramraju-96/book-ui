package com.example.bookui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookui.data.PopularBook
import com.example.bookui.data.popularBooks
import com.example.bookui.ui.MainScreen
import com.example.bookui.ui.detail.Book
import com.example.bookui.ui.detail.DetailScreen
import com.example.bookui.ui.home.HomeScreen
import com.example.bookui.ui.theme.BookUITheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookUITheme {
                // A surface container using the 'background' color from the theme

                val systemUiController = rememberSystemUiController()
                val statusBarColor = MaterialTheme.colors.surface
                val navigationBarColor = MaterialTheme.colors.background
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor,
                        darkIcons = useDarkIcons
                    )
                    systemUiController.setNavigationBarColor(
                        color = navigationBarColor,
                        darkIcons = useDarkIcons
                    )
                }

                    ProvideWindowInsets {
                        MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookUITheme {
        Greeting("Android")
    }
}