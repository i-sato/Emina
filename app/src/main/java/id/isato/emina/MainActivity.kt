package id.isato.emina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import id.isato.emina.ui.screen.main.MainScreen
import id.isato.emina.ui.theme.EminaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EminaTheme {
                MainScreen()
            }
        }
    }
}