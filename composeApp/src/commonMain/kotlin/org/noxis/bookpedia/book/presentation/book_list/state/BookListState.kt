package org.noxis.bookpedia.book.presentation.book_list.state

import androidx.compose.runtime.Immutable
import org.noxis.bookpedia.book.domain.Book
import org.noxis.bookpedia.core.presentation.UiText

@Immutable
data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = books,//emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)


private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Philipp Lackner"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.67854,
        ratingCount = 5,
        numPages = 100,
        numEditions = 3
    )
}