package com.voss.todolist.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            delay(2000)
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}