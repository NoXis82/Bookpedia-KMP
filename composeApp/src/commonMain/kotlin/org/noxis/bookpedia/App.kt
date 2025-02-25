package org.noxis.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.noxis.bookpedia.book.data.network.KtorRemoteBookDataSource
import org.noxis.bookpedia.book.data.repository.DefaultBookRepository
import org.noxis.bookpedia.book.presentation.book_list.screen.BookListScreenRoot
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel
import org.noxis.bookpedia.core.data.HttpClientFactory

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(engine)
                    )
                )
            ) },
            onBookClick = {

            }
        )
    }
}