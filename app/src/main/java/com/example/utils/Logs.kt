package com.example.utils

import android.nfc.Tag
import android.util.Log
import com.example.model.Email

fun ArrayList<Email>.log(tag:String="Logxyz"){

    var msg = " \n "

    for (i in this){
        msg += "${i.selected}   user ${i.user} \n"
    }

    Log.d(startUp+tag,msg)



}
const val startUp = "xyz"
const val DeleteItems:String = "DeletingItemsCalled"












;