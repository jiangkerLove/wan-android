package com.study.xml_view.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.study.xml_view.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebviewBinding.inflate(layoutInflater)
        binding.apply {
            tvTitle.text = intent.getStringExtra(TITLE)
            intent.getStringExtra(OPEN_URL)?.let { webView.loadUrl(it) }
        }
        setContentView(binding.root)
    }


    companion object {

        private const val TITLE = "title"
        private const val OPEN_URL = "open_url"

        fun jumpToActivity(activity: Activity, title: String, openUrl: String) {
            val intent = Intent(activity, WebViewActivity::class.java).apply {
                putExtra(TITLE, title)
                putExtra(OPEN_URL, openUrl)
            }
            activity.startActivity(intent)
        }
    }

}