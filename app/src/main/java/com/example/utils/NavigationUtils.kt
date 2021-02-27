package com.example.toolbar.utils

import android.content.Context
import android.content.Intent

fun Context.move(activity:Class<*>):Boolean{
    this.startActivity(Intent(this,activity))
    return true
}