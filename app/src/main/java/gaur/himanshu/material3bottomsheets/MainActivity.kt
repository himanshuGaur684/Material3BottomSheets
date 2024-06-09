package gaur.himanshu.material3bottomsheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import gaur.himanshu.material3bottomsheets.ui.theme.Material3BottomSheetsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            Material3BottomSheetsTheme {
                Surface(modifier = Modifier.safeContentPadding()) {
                    BottomSheet()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(modifier: Modifier = Modifier) {

    val isOpen = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val bottomPadding =
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding().value.toInt() + 8

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            scope.launch {
                sheetState.expand()
            }.invokeOnCompletion {
                isOpen.value = isOpen.value.not()
            }
        }) {
            Text(text = "Open")
        }
    }

    if (isOpen.value) {
        ModalBottomSheet(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .background(color = Color.Red)
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(bottom = bottomPadding.dp)
                    .background(color = Color.Yellow)
                   , contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        isOpen.value = isOpen.value.not()
                    }
                }) {
                    Text(text = "Close")
                }
            }
        }
    }


}

@Composable
fun ModalBottom(modifier: Modifier = Modifier) {


//    val scaffoldState = rememberBottomSheetScaffoldState()
//    val scope = rememberCoroutineScope()
//
//    BottomSheetScaffold(scaffoldState = scaffoldState,
//        sheetPeekHeight = 30.dp, sheetContent = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp), contentAlignment = Alignment.Center
//            ) {
//                Button(onClick = {
//                    scope.launch {
//                        scaffoldState.bottomSheetState.partialExpand()
//                    }
//
//                }) {
//                    Text(text = "Close")
//                }
//            }
//        }) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize(), contentAlignment = Alignment.Center
//        ) {
//            Button(onClick = {
//                scope.launch {
//                    scaffoldState.bottomSheetState.expand()
//                }
//            }) {
//                Text(text = "Open")
//            }
//        }
//    }
}


