package Database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var infoExtracted by mutableStateOf("GPS Co-ordinates : ")
    val offsetData by mutableStateOf("CO2 emission = 0.244 ton")
}