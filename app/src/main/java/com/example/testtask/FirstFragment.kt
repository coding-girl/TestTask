package com.example.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FirstFragment() : Fragment() {

    var text: String = ""

    constructor(text: String):this() {
        this.text = text
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_first, container, false)
        val context = activity as AppCompatActivity
        val field: TextView = v.findViewById(R.id.textView)
        field.text = text
        return v
    }
}