package UI

import Database.MainViewModel
import PdfRenderer.PdfBitmapConverter
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ProgressBar
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.carbonoffsetvalidator.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportInsightPage(
    navController: NavController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val pdfBitmapConverter = remember {
        PdfBitmapConverter(context)
    }

    var renderedPages by remember {
        mutableStateOf<List<Bitmap>>(emptyList())
    }

    LaunchedEffect(Unit) {
        viewModel.fileUri?.let { uri ->
            renderedPages = pdfBitmapConverter.PdftoBitmap(uri)
        }
    }

    val result = viewModel.result.observeAsState()

    Scaffold(
        topBar = {
            TopBar(viewModel, navController)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                //state = scrollState,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    //state = scrollState,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    // Header
                    Spacer(Modifier.height(100.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(renderedPages) { page ->
                            PdfPage(page = page)
                        }
                    }
                }
                Column(
                    //state = scrollState,
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    // Button
                    TextButton(
                        shape = CircleShape,
                        onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.getPrediction(viewModel.fileUri)
                            }
                            if(viewModel.result.value != null) {
                                navController.navigate("result")
                            }
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(PaddingValues(top = 16.dp))
                            .background(Orange, shape = CircleShape)
                        //.padding(PaddingValues(24.dp))
                    ) {
                        Text(
                            text = "Analyze",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500,
                            lineHeight = 1.sp,
                            letterSpacing = 0.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(PaddingValues(horizontal = 16.dp))
                        )
                    }
                    // Footer
                    Spacer(Modifier.height(60.dp))
                }
            }

            if(viewModel.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(if(isSystemInDarkTheme()) Color.Black.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.5f))
                ) {
                    CircularProgressIndicator(
                        color = Orange,
                        strokeWidth = 5.dp,
                        trackColor = Color.Gray.copy(alpha = 0.2f)
                    )
                }
            }
        }
    }
}

@Composable
fun PdfPage(
    page: Bitmap
) {
    AsyncImage(
        model = page,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(page.width.toFloat() / page.height.toFloat())
    )
}