package Navigation

import Database.MainViewModel
import UI.Homepage
import UI.ReportInsightPage
import UI.Resultpage
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable(route = "home") {
            Homepage(navController, viewModel)
        }
        composable(route = "report") {
            ReportInsightPage(navController, viewModel)
        }
        composable(route = "result") {
            Resultpage(navController, viewModel)
        }

    }
}