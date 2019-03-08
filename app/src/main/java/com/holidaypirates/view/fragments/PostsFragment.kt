package com.holidaypirates.view.fragments


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.facts.R
import com.holidaypirates.contract.PostModuleContract
import com.holidaypirates.model.jsonmodels.Post
import com.holidaypirates.model.jsonmodels.ResponseModel
import com.holidaypirates.presenter.DataPresenter
import com.holidaypirates.view.adapters.PostsAdapter
import kotlinx.android.synthetic.main.fragment_posts.*


class PostsFragment : Fragment(), PostModuleContract.View {
    lateinit var mContext: Context
    lateinit var mPresenter: PostModuleContract.Presenter
    private var mPageSize = 1

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = DataPresenter(this)
        mPresenter.getPosts(mPageSize)
    }

    override fun init() {
        rvPosts.adapter = PostsAdapter(mContext)
        rvPosts.layoutManager = LinearLayoutManager(mContext)
        rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    mPageSize = mPageSize.plus(1)
                    mPresenter.getPosts(mPageSize)
                }
            }
        })
    }

    override fun onSuccess(response: List<ResponseModel>) {
        val adapter = rvPosts.adapter as PostsAdapter
        adapter.addItems(response as ArrayList<Post>)
    }

    override fun onError(error: String) {
        Toast.makeText(mContext, "$error", Toast.LENGTH_SHORT).show()
    }
}
