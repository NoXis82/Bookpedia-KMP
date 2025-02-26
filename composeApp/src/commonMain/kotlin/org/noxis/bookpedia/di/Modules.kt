package org.noxis.bookpedia.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.noxis.bookpedia.book.SelectedBookViewModel
import org.noxis.bookpedia.book.data.network.KtorRemoteBookDataSource
import org.noxis.bookpedia.book.data.network.RemoteBookDataSource
import org.noxis.bookpedia.book.domain.BookRepository
import org.noxis.bookpedia.core.data.HttpClientFactory
import org.noxis.bookpedia.book.data.repository.DefaultBookRepository
import org.noxis.bookpedia.book.presentation.book_details.viewmodel.BookDetailViewModel
import org.noxis.bookpedia.book.presentation.book_list.viewmodel.BookListViewModel

expect val platformModule: Module


val sharedModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>() //{ KtorRemoteBookDataSource(get()) }
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}