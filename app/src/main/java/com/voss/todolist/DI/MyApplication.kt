package com.voss.todolist.DI

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.viewbinding.BuildConfig
import com.voss.todolist.TimberTree
import com.voss.todolist.Util.PreventFastClickUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication:Application() {
}