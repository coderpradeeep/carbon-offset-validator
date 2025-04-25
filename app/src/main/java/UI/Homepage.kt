package UI

import Database.MainViewModel
import PdfRenderer.PdfBitmapConverter
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.carbonoffsetvalidator.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.contracts.contract
import kotlin.coroutines.CoroutineContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Homepage(
    navController: NavController,
    viewModel: MainViewModel
) {
    //val context = LocalContext.current

    var result: Uri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    var launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            result = it
        }
    }

    Scaffold(
        topBar = {
            TopBar( viewModel, navController)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
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
                        //.weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    // Header
                    Spacer(Modifier.height(150.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(PaddingValues(24.dp))
                            .clickable {
                                launcher.launch(arrayOf("application/pdf",))
                            }
                    ) {
                        Text(
                            text = "Upload Document \n(format should be .pdf)",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.sp,
                            lineHeight = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(16.dp))
                        )
                    }
                }
                // PDFView and Button
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    TextButton(
                        shape = CircleShape,
                        onClick = {
                            viewModel.fileUri = result
                            if(viewModel.fileUri != Uri.EMPTY) {
                                navController.navigate("report")
                            }
                        },
                        modifier = Modifier
                            .background(Orange, shape = CircleShape)
                            .wrapContentSize()
                        //.padding(PaddingValues(24.dp))
                    ) {
                        Text(
                            text = "Upload",
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
        }
    }
}