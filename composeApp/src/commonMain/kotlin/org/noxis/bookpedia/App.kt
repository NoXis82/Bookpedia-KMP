package org.noxis.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.noxis.bookpedia.book.presentation.book_list.screen.BookListScreenRoot
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel

@Composable
@Preview
fun App() {
    val viewmodel = koinViewModel<BookListViewModel>()
    MaterialTheme {
        BookListScreenRoot(
            viewModel = viewmodel,
            onBookClick = {

            }
        )
    }
}