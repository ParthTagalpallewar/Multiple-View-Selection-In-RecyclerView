package com.example.multiselectinrecyclerview.utils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import com.example.multiselectinrecyclerview.model.Email

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

}
 fun View.setGradBackground(color: Int){
   val drawable =  GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 32f
        setColor(color)
    }

    this.background = drawable
}

fun TextView.setViewType(email: Email) {
    setTypeface(Typeface.DEFAULT, GetTexType(email))
}

fun GetTexType(email: Email): Int {
    return if (email.unread) Typeface.BOLD else Typeface.NORMAL
}

 fun TextView.setOval(@ColorInt color: Int): ShapeDrawable? {
   return ShapeDrawable(OvalShape()).apply {
       intrinsicHeight = this@setOval.height
       intrinsicWidth = this@setOval.width
       paint.color = color
   }

}

fun ArrayList<Email>.removeAllSeletedItems(){
    val newEmailsList: MutableList<Email> = ArrayList()
    for (email in this) {
        if (email.selected) newEmailsList.add(email)
    }
    this.removeAll(newEmailsList)

}

fun ArrayList<Email>.disSelectEmail(position: Int,selectedItems: SparseBooleanArray){

    selectedItems.delete(position)
    this[position].selected = false

    this.log("SelectedItemList_1")
}

fun ArrayList<Email>.selectEmail(position: Int,selectedItems: SparseBooleanArray){
    selectedItems.put(position,true)
    this[position].selected = true

    this.log("SelectedItemList_2")
}

fun ArrayList<Email>.unSelectAll(){
    for(email in this){

        Log.d(startUp+"unselect All",email.user + "  "+email.selected)
        var selection = email.selected
        if (selection) {
            email.selected = false
            Log.d(startUp+"unselect All-if",email.user + "  "+email.selected)
        }
    }

}

fun View.show(){
    this.visibility = View.VISIBLE
}
fun View.hide(){
    this.visibility = View.GONE
}
