package com.holidaypirates.view.adapters

import android.content.Context
import android.content.Intent
import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.app.facts.R
import com.holidaypirates.model.jsonmodels.Post
import com.holidaypirates.view.activities.DetailsActivity
import com.holidaypirates.view.activities.DetailsActivity.Companion.POST_ITEM

class PostsAdapter(private val mContext: Context) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    private var mPostList: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_post_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var postItem = mPostList[position]
        holder.tvTitle.text = postItem?.title
        holder.tvDescription.text = postItem?.body
    }

    override fun getItemCount(): Int {
        return mPostList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        internal var tvDescription: TextView = v.findViewById(R.id.tvDescription)
        internal var llCardItem: LinearLayout = v.findViewById(R.id.llCardItem)

        init {
            llCardItem.setOnClickListener {
                var position = adapterPosition
                var postItem = mPostList[position]
                mContext.startActivity(Intent(mContext, DetailsActivity::class.java)
                        .putExtra(POST_ITEM, postItem))
            }
        }
    }

    @UiThread
    fun addItems(items: ArrayList<Post>) {
        mPostList.addAll(items)
        notifyDataSetChanged()
    }
}
