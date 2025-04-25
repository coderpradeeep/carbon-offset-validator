package com.example.carbonoffsetvalidator

import Database.MainViewModel
import Navigation.NavGraph
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModelProvider
import com.example.carbonoffsetvalidator.ui.theme.CarbonOffsetValidatorTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel : MainViewModel
    lateinit var navController : NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarbonOffsetValidatorTheme {
                navController = rememberNavController()

                Scaffold() {
                    NavGraph(viewModel, navController)
                }
            }
        }
    }
}