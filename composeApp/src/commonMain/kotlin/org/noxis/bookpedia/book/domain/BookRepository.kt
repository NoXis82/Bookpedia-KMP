package org.noxis.bookpedia.book.domain

import org.noxis.bookpedia.core.domain.DataError
import org.noxis.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}