package com.example.jet2feed.datasource

import androidx.paging.PageKeyedDataSource
import com.example.jet2feed.api.RetrofitServiceBuilder
import com.example.jet2feed.api.interfaces.ArticlesApi
import com.example.jet2feed.model.Articles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesDataSource : PageKeyedDataSource<Int, Articles>() {

    private val service = RetrofitServiceBuilder.createService(ArticlesApi::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Articles>
    ) {
        val call = service.fetchArticles(FIRST_PAGE, PAGE_SIZE)

        call.enqueue(object : Callback<List<Articles>> {
            override fun onResponse(
                call: Call<List<Articles>>,
                response: Response<List<Articles>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    callback.onResult(apiResponse, null, FIRST_PAGE + 1)
                }
            }

            override fun onFailure(call: Call<List<Articles>>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Articles>
    ) {
        val call = service.fetchArticles(params.key, PAGE_SIZE)

        call.enqueue(object : Callback<List<Articles>> {
            override fun onResponse(
                call: Call<List<Articles>>,
                response: Response<List<Articles>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val key = params.key + 1
                    callback.onResult(apiResponse, key)
                }
            }

            override fun onFailure(call: Call<List<Articles>>, t: Throwable) {
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Articles>
    ) {
        val call = service.fetchArticles(params.key, PAGE_SIZE)

        call.enqueue(object : Callback<List<Articles>> {
            override fun onResponse(
                call: Call<List<Articles>>,
                response: Response<List<Articles>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val key = if (params.key > 1) params.key - 1 else 0
                    callback.onResult(apiResponse, key)
                }
            }

            override fun onFailure(call: Call<List<Articles>>, t: Throwable) {
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
    }
}
