package org.noxis.bookpedia.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.noxis.bookpedia.book.SelectedBookViewModel
import org.noxis.bookpedia.book.presentation.book_list.screen.BookListScreenRoot
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Route.BookGraph) {
            navigation<Route.BookGraph>(startDestination = Route.BookList) {

                composable<Route.BookList> {
                    val viewmodel = koinViewModel<BookListViewModel>()
                    val selectedBookVM = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedBookVM.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewmodel,
                        onBookClick = { book ->
                            selectedBookVM.onSelectBook(book)
                            navController.navigate(Route.BookDetail(book.id))
                        }
                    )
                }

                composable<Route.BookDetail> {
//                    val args = entry.toRoute<Route.BookDetail>()
                    val selectedBookVM = it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val selectedBook by selectedBookVM.selectedBook.collectAsStateWithLifecycle()

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Book detail screen! $selectedBook ")
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}