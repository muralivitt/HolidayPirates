package com.holidaypirates.presenter

import com.app.facts.R
import com.holidaypirates.contract.PostModuleContract
import com.holidaypirates.model.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import khee.app.vidya.App

class DataPresenter(view: PostModuleContract.View?) : PostModuleContract.Presenter {
    private var mView: PostModuleContract.View? = view
    private var mDataModel = DataModel()

    init {
        mView?.init()
    }

    /**
     * Get the posts with respective pageSize size  and update the UI
     */
    override fun getPosts(pageSize: Int) {
        if (!App.isNetworkAvailable()) {
            mView?.onError(App.instance.resources.getString(R.string.network_error))
            return
        }

        mDataModel.getPosts(pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mView?.onSuccess(response)
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mView?.onError("Failed to get posts!")
                        }
                )
    }

    /**
     * Get the comments for the post and update the UI
     */
    override fun getComments(postId: Int) {
        if (!App.isNetworkAvailable()) {
            mView?.onError(App.instance.resources.getString(R.string.network_error))
            return
        }

        mDataModel.getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mView?.onSuccess(response)
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mView?.onError("Failed to get comments!")
                        }
                )
    }

    /**
     * Get the Photos for the post and update the UI
     */
    override fun getPhotos(postId: Int) {
        if (!App.isNetworkAvailable()) {
            mView?.onError(App.instance.resources.getString(R.string.network_error))
            return
        }

        mDataModel.getPhotos(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mView?.onSuccess(response)
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mView?.onError("Failed to get photos!")
                        }
                )
    }

    /**
     * Get the User details with userID and update the UI
     */
    override fun getUserDetails(userId: Int) {
        if (!App.isNetworkAvailable()) {
            mView?.onError(App.instance.resources.getString(R.string.network_error))
            return
        }

        mDataModel.getUserDetails(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mView?.onSuccess(response)
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mView?.onError("Failed to get user details!")
                        }
                )
    }
}