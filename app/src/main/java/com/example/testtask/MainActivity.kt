package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var clickCount = 0
    private val client = OkHttpClient()
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        request("http://demo3005513.mockable.io/api/v1/entities/getAllIds")
        get_data_by_id(1)
        button_clicker()
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.host,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun request(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                var resStr: String = response.body()!!.string()
                var json = JSONObject(resStr)
                val jsonArray = json.getJSONArray("data")
            }
        })
    }

    private fun get_data_by_id(id: Int){
        val request = Request.Builder()
            .url("https://demo3005513.mockable.io/api/v1/object/$id")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                var resStr: String = response.body()!!.string()
                var json = JSONObject(resStr)
                val id = json.getString("id")
                val type = json.getString("type")

                if (type == "text"){
                    val msg = json.getString("message")
                    replaceFragment(FirstFragment(msg))
                }
                else if (type == "webview"){
                    val url = json.getString("url")
                    var fragments = SecondFragment(url)
                    replaceFragment(fragments)

                }
                else if(type == "image"){
                    val img = json.getString("url")
                    var fragment = SecondFragment(img)
                    replaceFragment(fragment)
                }
            }
        })

    }

    private fun button_clicker()
    {
        button = findViewById(R.id.btnNavigate)
        button.setOnClickListener {
            clickCount++
            if (clickCount >= 4 ) {
                clickCount = 1
            }
            get_data_by_id(clickCount)
        }
    }
}

