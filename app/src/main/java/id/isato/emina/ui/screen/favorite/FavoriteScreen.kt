package id.isato.emina.ui.screen.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.isato.emina.R
import id.isato.emina.ui.common.ErrorBox
import id.isato.emina.ui.screen.home.AnimeList

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.favoriteState.collectAsStateWithLifecycle().value.let { favorites ->
        when {
            favorites.isNotEmpty() -> {
                AnimeList(modifier = modifier, animeList = favorites, navigateToDetail = navigateToDetail)
            }
            else -> {
                ErrorBox(
                    modifier =  modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    message = stringResource(R.string.empty_list)
                )
            }
        }
    }
}