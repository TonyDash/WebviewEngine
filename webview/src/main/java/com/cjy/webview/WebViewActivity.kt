package com.cjy.webview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cjy.webview.databinding.ActivityWeviewBinding
import com.cjy.webview.utils.Constants


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weview)
        val webViewBinding: ActivityWeviewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_weview)

        intent.run {
            webViewBinding.actionBar.visibility = if (this.getBooleanExtra(
                    Constants.IS_SHOW_ACTION_BAR,
                    true
                )
            ) View.VISIBLE else View.GONE

            webViewBinding.title.text = this.getStringExtra(Constants.TITLE)
            webViewBinding.back.setOnClickListener {
                this@WebViewActivity.finish()
            }
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: Fragment =
            WebViewFragment.newInstance(intent.getStringExtra(Constants.URL), true)
        transaction.replace(R.id.web_view_fragment, fragment).commit()
    }

    fun updateTitle(title: String) {

    }

}