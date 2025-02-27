package org.noxis.bookpedia.book.presentation.book_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.noxis.bookpedia.app.Route
import org.noxis.bookpedia.book.domain.BookRepository
import org.noxis.bookpedia.book.presentation.book_details.action.BookDetailAction
import org.noxis.bookpedia.book.presentation.book_details.state.BookDetailState
import org.noxis.bookpedia.core.domain.onSuccess

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val bookId = savedStateHandle.toRoute<Route.BookDetail>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state//.asStateFlow()
        .onStart {
            fetchBookDescription()
//            //observeFavoriteStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: BookDetailAction) {
        when(action) {
            is BookDetailAction.OnSelectedBookChange -> {
                _state.update {
                    it.copy(book = action.book)
                }
            }
//            is BookDetailAction.OnFavoriteClick -> {
//                viewModelScope.launch {
//                    if(state.value.isFavorite) {
//                        bookRepository.deleteFromFavorites(bookId)
//                    } else {
//                        state.value.book?.let { book ->
//                            bookRepository.markAsFavorite(book)
//                        }
//                    }
//                }
//            }
            else -> Unit
        }
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository
                .getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update { it.copy(
                        book = it.book?.copy(
                            description = description
                        ),
                        isLoading = false
                    ) }
                }
        }
    }
}