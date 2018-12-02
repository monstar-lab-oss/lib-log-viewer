package com.nodesagency.logviewer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nodesagency.logviewer.screens.categories.presentation.CategoriesFragment

class LogViewerActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, LogViewerActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_viewer)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, CategoriesFragment.newInstance())
            .commit()
    }
}
