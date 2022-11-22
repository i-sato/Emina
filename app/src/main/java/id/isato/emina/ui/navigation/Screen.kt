package id.isato.emina.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailAnime : Screen("home/{animeId}") {
        fun createRoute(animeId: Long) = "home/$animeId"
    }
}
