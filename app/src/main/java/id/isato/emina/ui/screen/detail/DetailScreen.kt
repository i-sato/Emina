package id.isato.emina.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.isato.emina.ui.common.CircularProgressLoading
import id.isato.emina.ui.common.ErrorBox
import id.isato.emina.ui.common.NetworkImage
import id.isato.emina.ui.common.UiState
import id.isato.emina.R
import id.isato.emina.utils.TestTags

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    animeId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    viewModel.detailState.collectAsStateWithLifecycle(initialValue = UiState.Loading).value.let { detailState ->
        when (detailState) {
            is UiState.Loading -> {
                viewModel.getAnimeById(animeId)
                CircularProgressLoading(modifier = modifier)
            }
            is UiState.Success -> {
                val detail = detailState.data
                DetailContent(
                    modifier = modifier,
                    title = detail.title,
                    imageUrl = detail.imageUrl,
                    synopsis = detail.synopsis,
                    favorite = detail.isFavorite,
                    onBackClick = navigateBack,
                    onFavoriteClick = { isFavorite ->
                        viewModel.setFavorite(detail.malId, isFavorite)
                    }
                )
            }
            is UiState.Error -> {
                ErrorBox(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    message = detailState.errorMessage
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    synopsis: String,
    favorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {

    var isFavorite by rememberSaveable { mutableStateOf(favorite) }

    Box(modifier = modifier
        .testTag(TestTags.ANIME_DETAIL)
        .fillMaxSize()
        .padding(8.dp)) {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            Box {
                NetworkImage(
                    url = imageUrl,
                    modifier = modifier
                        .fillMaxWidth()
                        .size(250.dp)
                )
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = modifier
                        .size(32.dp)
                        .clickable { onBackClick.invoke() },
                )
                Surface(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .align(Alignment.BottomCenter),
                    color = Color.Black.copy(0.6F)
                ) {
                    Text(
                        text = title,
                        modifier = modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.LightGray
                    )
                }
            }
            Text(
                modifier = modifier.padding(top = 8.dp),
                text = synopsis,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )
        }
        FavoriteButton(modifier = modifier.align(Alignment.BottomEnd), isFavorite = isFavorite, onClick = {
            isFavorite = !isFavorite
            onFavoriteClick.invoke(isFavorite)
        })
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(shape = CircleShape, onClick = onClick, modifier = modifier) {
        val icon = when (isFavorite) {
            true -> Icons.Filled.Favorite
            false -> Icons.Filled.FavoriteBorder
        }
        Icon(imageVector = icon, contentDescription = stringResource(R.string.favorite))
    }
}