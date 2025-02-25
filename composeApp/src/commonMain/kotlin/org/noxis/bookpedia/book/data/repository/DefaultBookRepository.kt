package org.noxis.bookpedia.book.data.repository

import org.noxis.bookpedia.book.data.mappers.toBook
import org.noxis.bookpedia.book.data.network.RemoteBookDataSource
import org.noxis.bookpedia.book.domain.Book
import org.noxis.bookpedia.book.domain.BookRepository
import org.noxis.bookpedia.core.domain.DataError
import org.noxis.bookpedia.core.domain.Result
import org.noxis.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

}