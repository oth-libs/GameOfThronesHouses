package com.gothouses.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class House(
    @SerializedName("name") val name: String = "",
    @SerializedName("region") val region: String = "",
    @SerializedName("words") val words: String = "",
    @SerializedName("currentLord") val currentLord: String = ""
) : Parcelable