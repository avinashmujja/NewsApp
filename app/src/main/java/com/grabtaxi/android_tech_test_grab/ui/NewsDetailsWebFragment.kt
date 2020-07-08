package com.grabtaxi.android_tech_test_grab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.grabtaxi.android_tech_test_grab.Injectable
import com.grabtaxi.android_tech_test_grab.databinding.FragmentNewsDetailBinding

class NewsDetailsWebFragment : Fragment(),Injectable {

    lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = NewsDetailsWebFragmentArgs.fromBundle(arguments!!)
        loadUrlInWebView(binding.webView,binding.progressBar,params?.url!!)
    }

    private fun loadUrlInWebView(
        webView: WebView,
        progressBar: ProgressBar,
        url: String
    ) {
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if(progressBar == null)
                    return
                if(newProgress<100 && progressBar.visibility == View.GONE) {
                    progressBar.visibility = View.VISIBLE
                }

                progressBar.progress = newProgress

                if(newProgress == 100) {
                    progressBar.visibility = View.GONE
                }
            }
        }

        val webSettings = webView.settings
        webSettings.allowFileAccess = false
        webSettings.allowFileAccessFromFileURLs = false
        webSettings.allowUniversalAccessFromFileURLs = false
        webSettings.allowContentAccess = false
        webSettings.javaScriptEnabled = false
        webView.loadUrl(url)
    }

}