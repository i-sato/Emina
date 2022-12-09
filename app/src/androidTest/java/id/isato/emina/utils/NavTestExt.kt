package id.isato.emina.utils

import androidx.navigation.NavController
import com.google.common.truth.Truth.assertThat

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertThat(currentBackStackEntry?.destination?.route).isEqualTo(expectedRouteName)
}