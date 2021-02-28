package com.example.multiselectinrecyclerview.model

data class Email(
    var user: String,

    val subject: String,

    val preview: String,

    val date: String,

    val stared: Boolean = false,

    val unread: Boolean = false,

    var selected: Boolean = false
)