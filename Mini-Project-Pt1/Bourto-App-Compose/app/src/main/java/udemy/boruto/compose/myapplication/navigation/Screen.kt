package udemy.boruto.compose.myapplication.navigation

sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
//    pass some data
    object Details : Screen("details_screen/{heroId}"){
        fun passHeroId(heroId : Int): String{
            return "details_screen/$heroId"
        }
    }
    object Search : Screen("search_screen")
}
