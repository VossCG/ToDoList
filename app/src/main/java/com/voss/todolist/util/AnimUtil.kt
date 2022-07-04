package com.voss.todolist.util

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.voss.todolist.R

object AnimUtil {

    fun getRotateOpen(context: Context): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim)
    }
    fun getRotateClose(context: Context): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim)
    }
    fun getFromBottom(context: Context): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.from_buttom_anim)
    }
    fun getToBottom(context: Context): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.to_bottom_anim)
    }
}