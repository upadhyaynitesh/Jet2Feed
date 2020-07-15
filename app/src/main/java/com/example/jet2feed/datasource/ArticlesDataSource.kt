package com.example.jet2feed.datasource

import android.content.Context
import androidx.paging.PageKeyedDataSource
import androidx.room.Room
import com.example.jet2feed.api.RetrofitServiceBuilder
import com.example.jet2feed.api.interfaces.ArticlesApi
import com.example.jet2feed.database.AppDatabase
import com.example.jet2feed.interfaces.Listener
import com.example.jet2feed.model.Articles
import com.example.jet2feed.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesDataSource(
    val listener: Listener,
    private val hasInternet: Boolean,
    context: Context
) :
    PageKeyedDataSource<Int, Articles>() {

    private val service = RetrofitServiceBuilder.createService(ArticlesApi::class.java)
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Articles>
    ) {
        val call = service.fetchArticles(FIRST_PAGE, PAGE_SIZE)
        /*If internet connection is not available, fetch the data from database and show it in the list.
        * Set the nextPageKey to null as the whole data will be fetched from database in first go.
        * Note: There might be a better way to handle this, but for now I have implemented it this way.*/
        if (!hasInternet) {
            val list = db.articlesDao().retrieveArticles()
            callback.onResult(list, null, null)
            return
        }
        call.enqueue(object : Callback<List<Articles>> {
            override fun onResponse(
                call: Call<List<Articles>>,
                response: Response<List<Articles>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    listener.setArticles(apiResponse)
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
                    listener.setArticles(apiResponse)
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
