package Retrofit

import android.net.Uri
import retrofit2.http.Body
import retrofit2.http.POST

interface TodoApi {

    @POST("/process-report")
    suspend fun predict(@Body data : Uri) : String

}