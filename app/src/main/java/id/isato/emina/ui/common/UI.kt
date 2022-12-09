package id.isato.emina.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import id.isato.emina.R
import id.isato.emina.ui.theme.EminaTheme
import id.isato.emina.utils.TestTags

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        modifier = modifier.testTag(TestTags.ERROR),
        contentAlignment = Alignment.Center
    ) {
        Text(message)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorBoxPreview() {
    EminaTheme {
        ErrorBox(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            message = "Unable to get data."
        )
    }
}

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier
) {
    GlideImage(
        imageModel = { url },
        modifier = modifier,
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            Box(modifier = Modifier.matchParentSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_image_black_24),
                    contentDescription = "Error Image"
                )
            }
        },
        previewPlaceholder = R.drawable.ic_image_black_24
    )
}

@Composable
fun CircularProgressLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.testTag(TestTags.LOADING).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun OutlinedTextInput(
    input: String,
    label: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = input,
        onValueChange = onValueChange,
        label = { Text(text = label) }
    )
}