package com.example.ui.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.Px
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay

fun ImageView.loadImageWithGlide(url: String?) =
    Glide.with(this).load(url).centerCrop().into(this)

fun BottomNavigationView.show() {
    clearAnimation()
    animate().translationY(0f).duration = 300
    isVisible = true
}

suspend fun BottomNavigationView.hide() {
    clearAnimation()
    animate().translationY(height.toFloat()).duration = 300
    delay(300)
    isGone = true
}

fun View.setPadding(@Px vertical: Int,@Px horizontal: Int) {
    setPadding(horizontal, vertical, horizontal, vertical)
}