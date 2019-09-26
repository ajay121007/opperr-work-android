package com.expert.operrwork.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.expert.operrwork.util.Constant
import com.expert.operrwork.util.SharedPref
import com.expert.operrwork.util.helper.NetworkManager
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Akshay.
 * Api Client for retrofit Instance with Base Url
 */
class APIClient {

    private var mRetrofit: Retrofit? = null
    private val TAG = "RetrofitManager"
    private val mHeaderCacheControl = "Cache-Control"
    private val mHeaderPragma = "Pragma"
    private var mCache: Cache? = null
    private var mOkHttpClient: OkHttpClient? = null


    /**
     * getClient Retorfit instance
     * @param mContext use for store caching
     * @param mAPI  search for base url
     */
    fun getClient(mContext: Context): Retrofit {
        if (mRetrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            mRetrofit = Retrofit.Builder()
                .baseUrl(Constant.baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(mContext))
                .build()
        }
        return mRetrofit!!
    }

    /**
     * getOkHttpClient
     * @param mContext use for store caches
     */
    private fun getOkHttpClient(mContext: Context): OkHttpClient {
        val cacheSize = 10 *1024 *1024 // 10 MB
        val httpCacheDirectory = File(mContext.cacheDir, Constant.httpCacheDirectory)
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val networkCacheInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.HOURS)
                .build()

            response.newBuilder()
                .header(Constant.cacheControl, cacheControl.toString())
                .build()
        }

        val headerInterceptor = Interceptor {
            val original = it.request()

            val request = original.newBuilder()
                .header("token", SharedPref(mContext).getString(Constant.TOKEN))
                .method(original.method(), original.body())
                .build()

            it.proceed(request)
        }
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(logging)
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .build()

    }



    fun getRetrofitCache(mContext : Context, mAPI: Int): Retrofit {
        if (mRetrofit == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(provideOfflineCacheInterceptor(mContext))
                .addNetworkInterceptor(provideCacheInterceptor(mContext))
                .cache(provideCache(mContext))
            mOkHttpClient = httpClient.build()
            mRetrofit = Retrofit.Builder()
                .baseUrl(Constant.baseURL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(mOkHttpClient!!)
                .build()
        }
        return mRetrofit!!
    }
    //Cache store with size
    private fun provideCache(mContext : Context): Cache? {
        if (mCache == null) {
            try {
                mCache = Cache(
                    File(mContext.cacheDir, "http-cache"),
                    100 * 1024 * 1024) // 10 MB
            } catch (e: Exception) {
                Log.e(TAG, "Could not create Cache!")
            }
        }
        return mCache
    }

    //get the cache and remove particular time
    private fun provideCacheInterceptor(mContext : Context): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl: CacheControl = if (NetworkManager(mContext).checkInternetConnection()) {
                CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build()
            } else {
                CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
            }
            response.newBuilder()
                .removeHeader(mHeaderPragma)
                .removeHeader(mHeaderCacheControl)
                .header(mHeaderCacheControl, cacheControl.toString())
                .build()
        }
    }
    //getting the caches if internet are not available
    private fun provideOfflineCacheInterceptor(mContext: Context): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkManager(mContext).checkInternetConnection()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(mHeaderPragma)
                    .removeHeader(mHeaderCacheControl)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }
}