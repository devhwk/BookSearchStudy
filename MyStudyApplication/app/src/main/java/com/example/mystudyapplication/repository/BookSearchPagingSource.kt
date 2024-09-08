package com.example.mystudyapplication.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mystudyapplication.data.api.BookSearchApi
import com.example.mystudyapplication.data.model.Book
import com.example.mystudyapplication.util.Constants
import retrofit2.HttpException
import java.io.IOException

class BookSearchPagingSource(
    private val api: BookSearchApi,
    private val query: String,
    private val sort : String,
    private val target: String
) : PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = api.searchBooks(query, sort, pageNumber, params.loadSize, target)
            val endOfPaginationReached = response.body()?.meta?.isEnd!!

            val data = response.body()?.books!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (endOfPaginationReached) null else pageNumber + (params.loadSize / Constants.PAGING_SIZE)

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}