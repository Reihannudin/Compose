package udemy.boruto.compose.myapplication.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import udemy.boruto.compose.myapplication.presentation.screen.home.HomeScreen
import udemy.boruto.compose.myapplication.presentation.screen.splash.SplashScreen
import udemy.boruto.compose.myapplication.presentation.screen.welcome.WelcomeScreen
import udemy.boruto.compose.myapplication.utils.Constraint.DETAILS_ARGUMENT_KEY

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun setUpNavigation(navController: NavController){
    NavHost(
        navController = navController as NavHostController,
        startDestination =Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ){

        }
        composable(route = Screen.Search.route){
        }
    }
}