package com.holidaypirates.view.adapters

import android.content.Context
import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.facts.R
import com.holidaypirates.model.jsonmodels.Comment

class CommentsAdapter(private val mContext: Context) : RecyclerView.Adapter<CommentsAdapter.MyViewHolder>() {

    private var mCommentsList: ArrayList<Comment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_comment_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var commentsItem = mCommentsList[position]
        holder.tvTitle.text = commentsItem?.name
        holder.tvDescription.text = commentsItem?.body
        holder.tvUsername.text = commentsItem?.email
    }

    override fun getItemCount(): Int {
        return mCommentsList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        internal var tvUsername: TextView = v.findViewById(R.id.tvUser)
        internal var tvDescription: TextView = v.findViewById(R.id.tvDescription)

    }

    @UiThread
    fun addItems(items: ArrayList<Comment>) {
        mCommentsList.addAll(items)
        notifyDataSetChanged()
    }
}
