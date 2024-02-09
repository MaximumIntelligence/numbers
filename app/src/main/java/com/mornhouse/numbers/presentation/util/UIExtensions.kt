package com.mornhouse.numbers.presentation.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private fun LifecycleCoroutineScope.delayedLaunchWhenResumed(
    delay: Long,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launch {
        delay(delay)
        launchWhenResumed(block)
    }

fun View.hideKeyboard(lifecycleScope: LifecycleCoroutineScope? = null, next: (() -> Unit)? = null) {
    if (context != null) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
        lifecycleScope?.delayedLaunchWhenResumed(200L) {
            next?.invoke()
        }
    } else {
        lifecycleScope?.launchWhenResumed {
            next?.invoke()
        }
    }
}