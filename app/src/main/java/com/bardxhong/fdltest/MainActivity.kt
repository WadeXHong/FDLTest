package com.bardxhong.fdltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

const val EXTRA_KEY_LINK = "com.bardxhong.fdltest.MainActivity.link"

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent)
            .addOnSuccessListener {
                Log.i(TAG, "[getDynamicLink Success] ${it?.link?.toString()}")
            }
            .addOnFailureListener {
                Log.i(TAG, "[getDynamicLink Failed] ${it.message}")
            }
            .addOnCompleteListener {

                // launch homepage with delay in order to recognize if app open through MainActivity or not
                Thread(Runnable {
                    Thread.sleep(1000)
                    runOnUiThread {
                        startActivity(
                            Intent(this@MainActivity, RealHomePageActivity::class.java)
                                .apply {
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    putExtra(EXTRA_KEY_LINK, it.result?.link?.toString() ?: "null")
                                }
                        )
                        finish()
                    }
                }).start()
            }
    }
}
