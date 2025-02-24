package org.noxis.bookpedia.book.presentation.book_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.noxis.bookpedia.book.domain.Book
import org.noxis.bookpedia.book.presentation.book_list.action.BookListAction
import org.noxis.bookpedia.book.presentation.book_list.components.BookSearchBar
import org.noxis.bookpedia.book.presentation.book_list.state.BookListState
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel
import org.noxis.bookpedia.core.presentation.DarkBlue
import org.noxis.bookpedia.core.presentation.DesertWhite

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = {state},
        onAction = { action ->
            when(action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                is BookListAction.OnSearchQueryChange -> Unit
                is BookListAction.OnTabSelected -> Unit
            }
            viewModel.onAction(action)
        }
    )
}


@Composable
fun BookListScreen(
    state:() -> BookListState,
    onAction: (BookListAction) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookSearchBar(
            searchQuery = state().searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }
}