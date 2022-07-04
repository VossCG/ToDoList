package com.voss.todolist.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
    Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

fun closeKeyboard(view: View, activity: FragmentActivity) {
    val keyboardManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboardManager.hideSoftInputFromWindow(view.windowToken, 0)
}


