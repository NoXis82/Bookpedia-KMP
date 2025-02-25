package org.noxis.bookpedia.book.presentation.book_list.state

import androidx.compose.runtime.Immutable
import org.noxis.bookpedia.book.domain.Book
import org.noxis.bookpedia.core.presentation.UiText

@Immutable
data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
