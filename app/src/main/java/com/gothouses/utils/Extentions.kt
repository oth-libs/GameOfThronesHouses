package com.gothouses.utils

fun String.valueOrDash() = if (this.isNullOrEmpty()) "---" else this