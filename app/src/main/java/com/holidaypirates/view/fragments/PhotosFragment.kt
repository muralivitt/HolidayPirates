package com.holidaypirates.view.fragments


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.facts.R
import com.holidaypirates.contract.PostModuleContract
import com.holidaypirates.model.jsonmodels.Photo
import com.holidaypirates.model.jsonmodels.ResponseModel
import com.holidaypirates.presenter.DataPresenter
import com.holidaypirates.view.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_photos.*


class PhotosFragment : Fragment(), PostModuleContract.View {
    lateinit var mContext: Context
    lateinit var mPresenter: PostModuleContract.Presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = DataPresenter(this)
        mPresenter.getPhotos(1)
    }


    fun loadPhotosForPost(postId: Int) {
        mPresenter?.getPhotos(postId)
    }

    override fun init() {
        rvPhotos.adapter = PhotosAdapter(mContext)
        rvPhotos.layoutManager = GridLayoutManager(mContext, 4)
    }

    override fun onSuccess(response: List<ResponseModel>) {
        val adapter = rvPhotos.adapter as PhotosAdapter
        adapter.addItems(response as ArrayList<Photo>)
    }

    override fun onError(error: String) {
        Toast.makeText(mContext, "$error", Toast.LENGTH_SHORT).show()
    }
}
