package id.isato.emina.ui.screen.home

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.isato.emina.HiltTestActivity
import org.junit.Rule
import org.junit.Test
import id.isato.emina.ui.navigation.Screen
import id.isato.emina.ui.screen.main.MainScreen
import org.junit.Before
import id.isato.emina.R
import id.isato.emina.data.DummyData
import id.isato.emina.ui.theme.EminaTheme
import id.isato.emina.utils.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

@HiltAndroidTest
class HomeScreenTest {

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
            onRoot().printToLog("HomeScreenTestLabel")
        }
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun loadAnime_displayAnimeList() {
        mockWebServer.dispatcher = webServerDispatcher
        composeTestRule.run {
            onNodeWithText(composeTestRule.activity.getString(R.string.menu_home)).performClick()
            onNodeWithText(composeTestRule.activity.getString(R.string.search_anime)).assertIsDisplayed()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            waitUntilExists(hasTestTag(TestTags.ANIME_LIST))
            onNodeWithTag(TestTags.ANIME_LIST).performScrollToIndex(10)
        }
    }

    @Test
    fun filterAnime_displayUIProperly() {
        mockWebServer.dispatcher = webServerDispatcher
        val animeTitle = DummyData.generateAnimeResponses()[0].title
        composeTestRule.run {
            onNodeWithText(composeTestRule.activity.getString(R.string.menu_home)).performClick()
            onNodeWithText(activity.getString(R.string.search_anime)).assertIsDisplayed()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            waitUntilExists(hasTestTag(TestTags.ANIME_LIST))
            onNodeWithText(activity.getString(R.string.search_anime)).performTextInput(animeTitle.take(5))
            waitUntilExists(hasTestTag(TestTags.ANIME_LIST))
            waitUntilExists(hasText(animeTitle))
        }
    }

    @Test
    fun loadAnime_navigatesToDetail() {
        mockWebServer.dispatcher = webServerDispatcher
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
        }
    }

    @Test
    fun loadAnime_displayError() {
        mockWebServer.dispatcher = webServerDispatcher.apply { shouldThrowException(true) }
        composeTestRule.run {
            onNodeWithText(composeTestRule.activity.getString(R.string.menu_home)).performClick()
            onNodeWithText(activity.getString(R.string.search_anime)).assertIsDisplayed()
            waitUntilDoesNotExist(hasTestTag(TestTags.LOADING))
            waitUntilExists(hasTestTag(TestTags.ERROR))
        }
    }

}