package com.holidaypirates.network

import com.holidaypirates.model.jsonmodels.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  APIService : abstract layer for network calls
 */
interface APIService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @GET("/posts")
    fun getPostList(@Query("userId") page: Int): Observable<ArrayList<Post>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Int): Observable<ArrayList<Comment>>

    @GET("/users")
    fun getUserDetails(@Query("id") userId: Int): Observable<ArrayList<User>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") postId: Int): Observable<ArrayList<Photo>>
}