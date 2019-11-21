package com.bardxhong.fdltest

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class RealHomePageActivity : Activity() {

    private val link by lazy { findViewById<TextView>(R.id.tv_link) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.real_home_page)

        intent.getStringExtra(EXTRA_KEY_LINK)?.let {
            link.text = link.text.toString() + it
        }
    }
}