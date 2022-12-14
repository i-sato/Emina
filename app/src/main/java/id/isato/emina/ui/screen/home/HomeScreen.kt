package id.isato.emina.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import id.isato.emina.R
import id.isato.emina.ui.common.*
import id.isato.emina.ui.model.Anime
import id.isato.emina.ui.theme.EminaTheme
import id.isato.emina.utils.TestTags

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (animeId: Int) -> Unit
) {
    var input by rememberSaveable { mutableStateOf("") }
    Column {
        OutlinedTextInput(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            input = input,
            label = stringResource(R.string.search_anime),
            onValueChange = {
                input = it
                viewModel.filterAnime(it)
            }
        )
        viewModel.animeState.collectAsStateWithLifecycle().value.let { animeState ->
            when (animeState) {
                is UiState.Loading -> {
                    CircularProgressLoading(modifier = modifier)
                }
                is UiState.Success -> {
                    AnimeList(
                        animeList = animeState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }
                is UiState.Error -> {
                    ErrorBox(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        message = animeState.errorMessage
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeList(
    animeList: List<Anime>,
    modifier: Modifier = Modifier,
    navigateToDetail: (animeId: Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.testTag(TestTags.ANIME_LIST),
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = animeList,
            key = { it.malId }
        ) { anime ->
            AnimeItem(
                title = anime.title,
                imageUrl = anime.imageUrl,
                modifier = modifier.clickable {
                    navigateToDetail(anime.malId)
                }
            )
        }
    }
}

@Composable
fun AnimeItem(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp
    ) {
        Column {
            val density = LocalDensity.current.density
            NetworkImage(
                url = imageUrl,
                modifier = modifier
                    .fillMaxWidth()
                    .size(170.dp)
            )
            var padding by remember {
                mutableStateOf(0.dp)
            }
            Text(
                modifier = modifier.padding(start = 4.dp, end = 4.dp, bottom = padding),
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                onTextLayout = {
                    val lineCount = it.lineCount
                    val height = (it.size.height / density).dp
                    padding = if (lineCount > 1) 0.dp else height
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeItemPreview() {
    val anime = Anime.mock()
    EminaTheme {
        AnimeItem(title = anime.title, imageUrl = anime.imageUrl)
    }
}