package com.holidaypirates.contract

import com.holidaypirates.model.jsonmodels.*
import io.reactivex.Observable

/**
 * PostModuleContract: This contract describes the communication between view and presenter
 */
interface PostModuleContract {

    interface Model {
        fun getPosts(page: Int): Observable<ArrayList<Post>>
        fun getComments(postId: Int): Observable<ArrayList<Comment>>
        fun getPhotos(postId: Int): Observable<ArrayList<Photo>>
        fun getUserDetails(userId: Int): Observable<ArrayList<User>>
    }

    interface View {
        fun init()
        fun onSuccess(response: List<ResponseModel>)
        fun onError(error: String)
    }

    interface Presenter {
        fun getPosts(page: Int)
        fun getComments(postId: Int)
        fun getPhotos(postId: Int)
        fun getUserDetails(userId: Int)
    }
}