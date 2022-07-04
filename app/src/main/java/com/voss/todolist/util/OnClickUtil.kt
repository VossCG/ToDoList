package com.voss.todolist.util

import android.view.View
import android.widget.Toast


class PreventFastClickUtil(){
    companion object{
        private var lastClickTime:Long = 0

        fun isFastDoubleClick():Boolean{
            //取得現在時間
            val time = System.currentTimeMillis()
            //timeD :上次點擊時間與現在時間的時間差
            val timeD = time - lastClickTime
            if(timeD>0 && timeD<500){
                //若小於0.5秒則判定是快速點擊
                return true
            }
            else{
                //若大於0.5秒，把現在時間設為上次點擊時間
                lastClickTime = time
                return false
            }
        }

    }
}
fun View.setPreventQuickerClick(action: () -> Unit) {
    setOnClickListener {
        if (!PreventFastClickUtil.isFastDoubleClick()) {
            action.invoke()
        } else
            Toast.makeText(context, "click too fast", Toast.LENGTH_SHORT).show()
    }
}
