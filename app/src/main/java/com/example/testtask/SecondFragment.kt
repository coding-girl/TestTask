package com.example.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondFragment() : Fragment() {

    private lateinit var webView: WebView
    var url: String = ""

    constructor(url: String):this() {
        this.url = url
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_second, container, false)
        webView = v.findViewById(R.id.webView_page)
        val context = activity as AppCompatActivity
        val frag: WebView = v.findViewById(R.id.webView_page)
        webViewSetup(url)
        return v
    }

    fun  webViewSetup(url: String) {
        webView.setVisibility(View.VISIBLE);
        webView.post(Runnable {
            webView.settings.setJavaScriptEnabled(true)
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        })

    }}