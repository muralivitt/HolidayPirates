package com.holidaypirates.view.adapters

import android.app.Activity
import android.content.Context
import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.app.facts.R
import com.holidaypirates.model.jsonmodels.Photo
import com.holidaypirates.utils.ImageUtils

class PhotosAdapter(private val mContext: Context) : RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {

    private var mPhotosList: ArrayList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_photo_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var photoItem = mPhotosList[position]
        ImageUtils.loadImage(photoItem.thumbnailUrl, holder.ivThumbnail)
    }

    override fun getItemCount(): Int {
        return if (mPhotosList.size > 12) 12 else mPhotosList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var ivThumbnail: ImageView = v.findViewById(R.id.ivThumbnail)

        init {
            ivThumbnail.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ImageUtils.imageHeight(ivThumbnail?.context as Activity, 4))
        }
    }

    @UiThread
    fun addItems(items: ArrayList<Photo>) {
        mPhotosList.addAll(items)
        notifyDataSetChanged()
    }
}
