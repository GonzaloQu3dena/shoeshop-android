package com.qu3dena.shoeshop.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.qu3dena.shoeshop.android.core.ui.screen.MainScreen
import com.qu3dena.shoeshop.android.core.ui.theme.ShoeShopAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeShopAndroidTheme {

            }
        }
    }
}
