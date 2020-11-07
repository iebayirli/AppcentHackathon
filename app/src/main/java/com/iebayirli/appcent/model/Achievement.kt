package com.iebayirli.appcent.model


data class Achievement(
    val name: String? = null,
    val limit: Int? = null,
    val icon: String? = null
) {
    var isRewarded: Boolean = false
}