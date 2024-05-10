package viewModel

import apis.Apis
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import models.Posts

data class PostUiState(val posts: Posts = Posts())

class PostsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    fun getPosts() = viewModelScope.launch {
        val posts = Apis().getPosts()
        _uiState.update {
            it.copy(posts = posts)
        }
    }
}