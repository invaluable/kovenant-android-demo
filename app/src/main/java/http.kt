package nl.mplatvoet.komponents.kovenant.android.demo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.task
import nl.komponents.kovenant.then
import java.io.ByteArrayInputStream
import java.nio.charset.Charset

class FuelHttpService {
    fun textUrl(url: String, parameters: List<Pair<String, Any?>>? = null): Promise<String, Exception> {
        return Fuel.get(url, parameters).promise() then {
            val (request, response, bytes) = it
            String(bytes, Charset.forName(response.contentTypeEncoding))
        }
    }

    fun bitmapUrl(url: String): Promise<Bitmap, Exception> {
        return Fuel.get(url).promise() then {
            BitmapFactory.decodeStream(ByteArrayInputStream(it.third))
        }
    }
}


fun Request.promise(): Promise<Triple<Request, Response, ByteArray>, Exception> = task {
    response()
}

val Response.contentTypeEncoding: String
    get() = contentTypeEncoding()

fun Response.contentTypeEncoding(default: String = "utf-8"): String {
    val contentType: String = httpResponseHeaders["Content-Type"]?.first() ?: return default
    return contentType.substringAfterLast("charset=", default).substringAfter(' ', default)
}





