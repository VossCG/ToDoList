package com.voss.todolist.data.di

import android.app.Application
import com.voss.todolist.data.room.EventDao
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application()