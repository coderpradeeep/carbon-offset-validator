package Database

import Retrofit.RetrofitInstance
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var apiResponse = RetrofitInstance.api

    var infoExtracted by mutableStateOf("GPS Co-ordinates : ")
    var offsetData by mutableStateOf("CO2 emission = 0.244 ton")

    private val _result = MutableLiveData<String?>(null)
    val result: LiveData<String?> get() =_result

    var isLoading by mutableStateOf(false)

    var fileUri: Uri by mutableStateOf(Uri.EMPTY)

    @OptIn(InternalCoroutinesApi::class)
    suspend fun getPrediction(data: Uri) {
        isLoading = true
        viewModelScope.launch() {
            try {
                Log.i("Request Data", data.toString())

                val result : String = apiResponse.predict(data)
                this@MainViewModel._result.value = result

                Log.i("Success", "${result}")
            }
            catch (e: Exception) {
                // Log other exceptions
                Log.e("Error", "API call failed: ${e.message}", e)
            }
            isLoading = false
        }
    }
}