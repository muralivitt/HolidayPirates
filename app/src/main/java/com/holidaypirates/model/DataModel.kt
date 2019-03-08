package com.holidaypirates.model

import com.holidaypirates.contract.PostModuleContract
import com.holidaypirates.network.NetworkController
import com.holidaypirates.model.jsonmodels.Comment
import com.holidaypirates.model.jsonmodels.Photo
import com.holidaypirates.model.jsonmodels.Post
import com.holidaypirates.model.jsonmodels.User
import io.reactivex.Observable


/**
 * DataModel : It will provides required data to the presenter to execute business logic.
 */
class DataModel : PostModuleContract.Model {

    override fun getPosts(page:Int): Observable<ArrayList<Post>> {
        return NetworkController.getAPIService().getPostList(page)
    }

    override  fun getComments(postId:Int): Observable<ArrayList<Comment>>{
        return NetworkController.getAPIService().getComments(postId)
    }

    override fun getPhotos(postId:Int): Observable<ArrayList<Photo>>{
        return NetworkController.getAPIService().getPhotos(postId)
    }

    override fun getUserDetails(userId:Int): Observable<ArrayList<User>>{
        return NetworkController.getAPIService().getUserDetails(userId)
    }
}