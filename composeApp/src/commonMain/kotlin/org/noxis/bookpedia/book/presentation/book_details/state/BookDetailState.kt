package org.noxis.bookpedia.book.presentation.book_details.state

import androidx.compose.runtime.Immutable
import org.noxis.bookpedia.book.domain.Book

@Immutable
data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
