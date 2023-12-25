package com.example.data.remote.paging_src

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.ProductsApiService
import com.example.main.domain.model.ProductModel
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(
    private val productsApiService: ProductsApiService,
    private val text: String?
) : PagingSource<Int, ProductModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = productsApiService.getProductList(
                search = text ,page = nextPageNumber, limit = params.loadSize)
            val nextPage = Uri.parse(response.next).getQueryParameter("page")?.toInt()
            val prevPage = Uri.parse(response.previous).getQueryParameter("page")?.toInt()
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
            anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
        }
    }
}