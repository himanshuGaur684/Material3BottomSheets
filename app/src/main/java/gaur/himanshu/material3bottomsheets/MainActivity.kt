package gaur.himanshu.material3bottomsheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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
                    NavigationDrawer()
                }
            }
        }
    }
}

@Composable
fun NavigationDrawer(modifier: Modifier = Modifier) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        scrimColor = Color.Red,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                LazyColumn {
                    repeat(4) {
                        item {
                            Text(
                                text = it.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
        }) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                if (drawerState.isOpen) {
                    scope.launch {
                        drawerState.close()
                    }
                } else {
                    scope.launch {
                        drawerState.open()
                    }
                }
            }) {
                Text(text = "toggle")
            }
        }

    }

}





