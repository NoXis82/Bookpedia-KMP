package org.noxis.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.noxis.bookpedia.book.presentation.book_list.screen.BookListScreenRoot
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = {

            }
        )
    }
}