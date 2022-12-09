package id.isato.emina.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatcher: Dispatcher() {

    private var shouldThrowException = false

    fun shouldThrowException(shouldThrowException: Boolean) {
        this.shouldThrowException = shouldThrowException
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        val responseBody: String? = when {
            request.path.equals(
                "/top/anime",
                true
            ) && !shouldThrowException -> JsonConverter.readStringFromFile("anime_success_response.json")
            else -> null
        }
        return when (responseBody) {
            null -> MockResponse().setResponseCode(500)
            else -> MockResponse().setResponseCode(200).setBody(responseBody)
        }
    }

}