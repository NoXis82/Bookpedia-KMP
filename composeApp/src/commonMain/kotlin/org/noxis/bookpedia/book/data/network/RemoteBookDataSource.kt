package org.noxis.bookpedia.book.data.network

import org.noxis.bookpedia.book.data.dto.BookWorkDto
import org.noxis.bookpedia.book.data.dto.SearchResponseDto
import org.noxis.bookpedia.core.domain.DataError
import org.noxis.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}