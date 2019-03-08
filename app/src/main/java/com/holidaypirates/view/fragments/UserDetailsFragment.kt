package com.holidaypirates.view.fragments


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.app.facts.R
import com.holidaypirates.contract.PostModuleContract
import com.holidaypirates.model.jsonmodels.ResponseModel
import com.holidaypirates.model.jsonmodels.User
import com.holidaypirates.presenter.DataPresenter
import com.holidaypirates.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_user_details.*
import java.util.*


class UserDetailsFragment : Fragment(), PostModuleContract.View {
    lateinit var mContext: Context
    lateinit var mPresenter: PostModuleContract.Presenter
    var mUser: User? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = DataPresenter(this)
    }

    fun loadUserDetails(userId: Int) {
        mPresenter?.getUserDetails(userId)
    }

    override fun init() {
        ivUserImage.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ImageUtils.imageHeight(ivUserImage?.context as Activity, 3))

        //Map
        imgMap.setOnClickListener {
            if (mUser != null) {
                openMap(mUser?.address?.geo?.lat, mUser?.address?.geo?.lng)
            }
        }

        //Email
        imgEmail.setOnClickListener {
            if (mUser != null) {
                sendEmail(mUser?.email)
            }
        }

        //Website
        imgWeb.setOnClickListener {
            if (mUser != null) {
                openWebsite(mUser?.website)
            }
        }
    }

    override fun onSuccess(response: List<ResponseModel>) {
        if (response?.size > 0) {
            setUserDetails(response[0] as User)
        }
    }

    private fun setUserDetails(user: User) {
        mUser = user
        tvUserName?.text = "${user.name}"
        tvLocation?.text = "${user.address?.street}, ${user.address?.city}"
    }


    override fun onError(error: String) {
        Toast.makeText(mContext, "$error", Toast.LENGTH_SHORT).show()
    }

    /**
     * Create email Intent and open email apps
     */
    fun sendEmail(emailId: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, emailId)
        intent.putExtra(Intent.EXTRA_SUBJECT, mContext.resources.getString(R.string.app_name))
        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivity(intent)
        }
    }


    /**
     * Create browse Intent and open browser apps
     */
    fun openWebsite(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        if (intent.resolveActivity(activity?.packageManager) != null) {
            startActivity(intent)
        }
    }

    /**
     * Open map with given lat, lng
     */
    fun openMap(latitude: String?, longitude: String?) {
        val uri = String.format(Locale.ENGLISH, "geo:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}
