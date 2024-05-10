import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import viewModel.PostsViewModel

@Composable
@Preview
fun App() {
    val scrollState = rememberLazyListState()
    MaterialTheme {
        val postsViewModel = getViewModel(Unit, viewModelFactory { PostsViewModel() })
        val uiState by postsViewModel.uiState.collectAsState()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(title = { Text("Posts") })

            LaunchedEffect(postsViewModel) { postsViewModel.getPosts() }

            AnimatedVisibility(uiState.posts.posts.isEmpty()) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            AnimatedVisibility(uiState.posts.posts.isNotEmpty()) {
                LazyColumn(state = scrollState) {
                    items(count = uiState.posts.posts.size) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                uiState.posts.posts[it].title,
                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600)
                            )
                            Text(uiState.posts.posts[it].body)
                        }
                    }
                }
            }


        }
    }
}