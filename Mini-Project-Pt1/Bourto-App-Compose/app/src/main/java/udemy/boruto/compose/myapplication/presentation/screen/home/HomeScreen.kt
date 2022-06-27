package udemy.boruto.compose.myapplication.presentation.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import udemy.boruto.compose.myapplication.navigation.Screen
import udemy.boruto.compose.myapplication.presentation.common.ListContent
import udemy.boruto.compose.myapplication.presentation.component.RatingWidget
import udemy.boruto.compose.myapplication.ui.theme.LARGE_PADDING

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    homeViewModel : HomeViewModel = hiltViewModel(),
    navController : NavHostController
){

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            HomeTopBar (onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController
            )
        }
    )
}