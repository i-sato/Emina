package id.isato.emina.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import id.isato.emina.ui.model.AnimeDetail
import id.isato.emina.R

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
                    detail = detail,
                    onBackClick = navigateBack
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
    detail: AnimeDetail,
    onBackClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            NetworkImage(
                url = detail.imageUrl,
                modifier = modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .alpha(0.7F)
            )
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = modifier
                    .size(32.dp)
                    .clickable { onBackClick.invoke() },
            )
            Text(
                text = detail.title,
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            modifier = modifier.padding(top = 8.dp),
            text = detail.synopsis,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Justify
        )
    }
}