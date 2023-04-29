package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uvandroid.progressindicator.ProgressIndicator
import com.uvandroid.progressindicator.ProgressIndicatorView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pi = findViewById<ProgressIndicatorView>(R.id.progress_indicator)

        pi.setProgressIndicator(
            ProgressIndicator(
                5,
                2,
                com.uvandroid.progressindicator.R.drawable.ic_progress_current,
                com.uvandroid.progressindicator.R.drawable.ic_progress_current,
                com.uvandroid.progressindicator.R.drawable.ic_progress_current
            )
        )
    }
}