package ro.lazarl.converter.common.api.mock

import android.content.res.AssetManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection

class MockResponseInterceptor(
    private val assetManager: AssetManager,
    private val responseMap: Map<String, String>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val mockResponseFile = responseMap[chain.request().url.toString()]
        if (null != mockResponseFile) {
            val mockResponse = readAssetsFile(mockResponseFile)
            return Response.Builder()
                .code(HttpURLConnection.HTTP_OK)
                .message("mocked")
                .body(mockResponse.toResponseBody("application/json".toMediaType()))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .build()
        }
        return chain.proceed(chain.request())
    }

    private fun readAssetsFile(filePath: String): String =
        assetManager
            .open(filePath)
            .use { inputStream ->
                InputStreamReader(inputStream, "utf-8")
                    .use { inputStreamReader -> BufferedReader(inputStreamReader, 8 * 1024).readLines() }
            }
            .let { lines ->
                buildString { lines.forEach { line -> append(line) } }
            }
}