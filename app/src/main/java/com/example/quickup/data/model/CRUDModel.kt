package com.example.quickup.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class CRUDModel (
        @SerializedName("status")
        var status: Int,
        @SerializedName("message")
        var message: String?,


        )