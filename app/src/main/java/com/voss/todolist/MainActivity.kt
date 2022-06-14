package com.voss.todolist

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.BuildConfig
import com.voss.todolist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private val bottomViewGoneIdList = arrayListOf<Int>(
        R.id.contentMonthlyFragment,
        R.id.browseMonthFragment,
        R.id.editEventFragment,
        R.id.updateEventFragment,
        R.id.startSearchFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) Timber.plant(TimberTree())
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navBottom = binding.navBottom

        mNavController = navHostFragment.navController
        navBottom.setupWithNavController(mNavController)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (bottomViewGoneIdList.contains(destination.id)) {
                navBottom.visibility = View.GONE
            } else {
                navBottom.visibility = View.VISIBLE
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        clearFocusOnOutsideClick(ev)
        return super.dispatchTouchEvent(ev)
    }

    // 測試看看是否能成功收起鍵盤
    private fun closeKeyboard(isShow: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (isShow) {
                window?.insetsController?.show(WindowInsets.Type.ime())
            } else {
                window?.insetsController?.hide(WindowInsets.Type.ime())
            }
        } else {
            ViewCompat.getWindowInsetsController(binding.navBottom).let { controller ->
                if (isShow) {
                    controller?.show(WindowInsetsCompat.Type.ime())
                } else {
                    controller?.hide(WindowInsetsCompat.Type.ime())
                }
            }
        }
    }
    private fun clearFocusOnOutsideClick(event: MotionEvent?) {
        currentFocus?.apply {
            if (event == null) return@apply
            if (this is EditText && event.action == MotionEvent.ACTION_DOWN
                || event.action == MotionEvent.ACTION_MOVE
            ) {
                // 設定當前View的左上角頂點的x,y 座標
                // 這邊都設為 0,0 開始
                val location = intArrayOf(0, 0)
                this.getLocationOnScreen(location)
                // 左邊為 x = 0
                val left = location[0]
                // 上方為 y = 0
                val top = location[1]
                // 所以右方座標 就是當前 left 的 x 座標起點 加上 物件的寬度
                val right = left + this.measuredWidth
                // 所以下方座標 就是當前 top  的 y 座標起點 加上 物件的高度
                val bottom = top + this.measuredHeight
                // 取得當前點選的 x,y 座標
                val x = event.rawX
                val y = event.rawY

                // 開始判斷點選位置與物件位置的關係
                // 若 點選的x座標 在right 與 left top 與 bottom 的range 之間
                // 就代表點擊的地方，就是物件本身，就不收起鍵盤跟取消焦點
                if (x >= left && x <= right && y >= top && y <= bottom) {
                    //如果點擊的是自身，直接返回結束
                    return@apply
                }
                clearFocus()
                imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
}

