package com.holidaypirates.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.facts.R
import com.holidaypirates.model.jsonmodels.Post
import com.holidaypirates.view.fragments.CommentsFragment
import com.holidaypirates.view.fragments.PhotosFragment
import com.holidaypirates.view.fragments.UserDetailsFragment
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val POST_ITEM = "PostItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val post = intent.extras.getSerializable(POST_ITEM) as Post
        setUserDetails(post)
        setPhotos(post)
        setComments(post)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUserDetails(post: Post) {
        var fragmentComments = fragmentManager.findFragmentById(R.id.fragmentUserDetails) as UserDetailsFragment?
        fragmentComments?.loadUserDetails(post.userId)
    }

    private fun setPhotos(post: Post) {
        var fragmentPhotos = fragmentManager.findFragmentById(R.id.fragmentPhotos) as PhotosFragment?
        fragmentPhotos?.loadPhotosForPost(post.id)
    }

    private fun setComments(post: Post) {
        var fragmentComments = fragmentManager.findFragmentById(R.id.fragmentComments) as CommentsFragment?
        fragmentComments?.loadCommentsForPost(post.id)
    }
}
