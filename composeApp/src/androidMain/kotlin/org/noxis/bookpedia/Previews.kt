package org.noxis.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.noxis.bookpedia.book.domain.Book
import org.noxis.bookpedia.book.presentation.book_list.components.BookList
import org.noxis.bookpedia.book.presentation.book_list.components.BookListItem
import org.noxis.bookpedia.book.presentation.book_list.components.BookSearchBar
import org.noxis.bookpedia.book.presentation.book_list.screen.BookListScreen
import org.noxis.bookpedia.book.presentation.book_list.state.BookListState

@Composable
@Preview
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        BookSearchBar(
            searchQuery = "Kotlin",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun BookListItemPreview() {
    BookListItem(
        book = Book(
            id = "",
            title = "Title",
            imageUrl = "",
            authors = listOf("Author"),
            description = "Description",
            languages = listOf("RU"),
            firstPublishYear = "2022",
            averageRating = 4.5,
            ratingCount = 4,
            numPages = 200,
            numEditions = 2
        ),
        onClick = {}
    )
}

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


@Preview
@Composable
private fun BookListPreview() {
    BookList(
        books = books,
        onBookClick = {}
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = {
            BookListState(searchResults = books)
        },
        onAction = {}
    )
}