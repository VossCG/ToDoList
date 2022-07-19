package com.voss.todolist.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity


fun disPlayToastShort(context: Context, message:String){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}

fun disPlayToastLong(context: Context, message:String){
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}

fun View.setPreventQuickerClick(action: () -> Unit) {
    setOnClickListener {
        if (!PreventFastClickUtil.isFastDoubleClick()) {
            action.invoke()
        } else
            Toast.makeText(context, "click too fast", Toast.LENGTH_SHORT).show()
    }
}

fun pxToDp(context: Context, px: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

fun dpToPx(context: Context, dp: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun closeKeyboard(view: View, activity: FragmentActivity) {
    val keyboardManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboardManager.hideSoftInputFromWindow(view.windowToken, 0)
}