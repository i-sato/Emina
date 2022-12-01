package id.isato.emina.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import id.isato.emina.ui.common.ErrorBox
import id.isato.emina.ui.common.NetworkImage
import id.isato.emina.ui.common.UiState
import id.isato.emina.ui.model.Profile
import id.isato.emina.ui.theme.EminaTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    viewModel.profileState.collectAsState(initial = UiState.Loading).value.let { profileState ->
        when (profileState) {
            is UiState.Loading -> {
                viewModel.getProfile()
            }
            is UiState.Success -> {
                ProfileContent(
                    modifier = modifier,
                    profile = profileState.data
                )
            }
            is UiState.Error -> {
                ErrorBox(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    message = profileState.errorMessage
                )
            }
        }
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    profile: Profile
) {
    Column(modifier = modifier.fillMaxSize()) {
        NetworkImage(
            url = profile.backdrop, modifier = modifier
                .fillMaxWidth()
                .size(250.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            NetworkImage(
                url = profile.avatar, modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = profile.userName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = profile.occupation, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Text(
                    text = profile.bio,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    EminaTheme {
        ProfileContent(profile = Profile.mock())
    }
}