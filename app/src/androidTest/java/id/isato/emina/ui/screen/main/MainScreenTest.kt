package id.isato.emina.ui.screen.main

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.isato.emina.HiltTestActivity
import id.isato.emina.R
import id.isato.emina.data.DummyData
import id.isato.emina.ui.navigation.Screen
import id.isato.emina.ui.theme.EminaTheme
import id.isato.emina.utils.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private val mockWebServer = MockWebServer()
    private val webServerDispatcher = MockWebServerDispatcher()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.apply {
            setContent {
                EminaTheme {
                    navController = TestNavHostController(LocalContext.current)
                    navController.navigatorProvider.addNavigator(ComposeNavigator())
                    MainScreen(navController = navController)
                }
            }
            onRoot().printToLog("MainScreenTestLabel")
        }
        mockWebServer.apply {
            start(8080)
            dispatcher = webServerDispatcher
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun navigatesAllScreen_displayUIProperly() {
        val animeTitle = DummyData.generateAnimeResponses()[0].title
        composeTestRule.run {
            onNodeWithText(composeTestRule.activity.getString(R.string.menu_home)).performClick()
            onNodeWithText(activity.getString(R.string.search_anime)).assertIsDisplayed()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            waitUntilExists(hasTestTag(TestTags.ANIME_LIST))
            onNodeWithTag(TestTags.ANIME_LIST).performScrollToIndex(5)
            onNodeWithText(animeTitle).performClick()

            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            onNodeWithText(animeTitle).assertIsDisplayed()
            onNodeWithTag(TestTags.ANIME_DETAIL).assertIsDisplayed()
            Espresso.pressBack()

            onNodeWithText(composeTestRule.activity.getString(R.string.menu_favorite)).performClick()
            onNodeWithText(activity.getString(R.string.empty_list)).assertIsDisplayed()

            onNodeWithText(composeTestRule.activity.getString(R.string.menu_profile)).performClick()
            waitUntilExists(hasTestTag(TestTags.PROFILE))
            onNodeWithText("Iman").assertIsDisplayed()
        }
    }

    @Test
    fun loadAnime_animeIsFavorite() {
        val animeTitle = DummyData.generateAnimeResponses()[0].title
        composeTestRule.run {
            onNodeWithText(composeTestRule.activity.getString(R.string.menu_home)).performClick()
            onNodeWithText(activity.getString(R.string.search_anime)).assertIsDisplayed()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            waitUntilExists(hasTestTag(TestTags.ANIME_LIST))
            onNodeWithTag(TestTags.ANIME_LIST).performScrollToIndex(5)
            onNodeWithText(animeTitle).performClick()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            onNodeWithText(animeTitle).assertIsDisplayed()
            onNodeWithTag(TestTags.ANIME_DETAIL).assertIsDisplayed()
            navController.assertCurrentRouteName(Screen.DetailAnime.route)

            onNodeWithContentDescription(activity.getString(R.string.add_favorite)).performClick()
            Espresso.pressBack()
            onNodeWithText(activity.getString(R.string.menu_favorite)).performClick()
            onNodeWithText(animeTitle).assertIsDisplayed()
        }
    }

}