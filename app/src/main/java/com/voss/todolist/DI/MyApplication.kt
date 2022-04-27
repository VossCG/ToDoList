package com.voss.todolist.DI

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.voss.todolist.TimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication:Application() {

}