package com.example.ui.extensions

import android.view.View
import android.widget.ImageView
import androidx.annotation.Px
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

fun ImageView.loadImageWithGlide(url: String?) =
    Glide.with(this).load(url).centerCrop().into(this)

fun BottomNavigationView.show() {
    clearAnimation()
    animate().translationY(0f).duration = 300
}

fun BottomNavigationView.hide() {
    clearAnimation()
    animate().translationY(height.toFloat()).duration = 300
}

fun View.setPadding(@Px vertical: Int,@Px horizontal: Int) {
    setPadding(horizontal, vertical, horizontal, vertical)
}