package com.example.data.remote.paging_src

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api_service.ProductsApiService
import com.example.main.domain.model.ProductModel
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(
    private val productsApiService: ProductsApiService,
    private val text: String?,
    private val categoryName: String?,
) : PagingSource<Int, ProductModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductModel> {
        val page = params.key ?: 1
        return try {
            val response = productsApiService.getProductList(
                search = text, page = page, limit = params.loadSize, categoryName = categoryName
            )
            val nextPage =
                response.next?.let { Uri.parse(response.next).getQueryParameter("page")?.toInt() }
            val prevPage = response.previous?.let {
                Uri.parse(response.previous).getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                nextKey = nextPage,
                prevKey = prevPage
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