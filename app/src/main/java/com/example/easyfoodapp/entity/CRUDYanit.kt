package com.example.easyfoodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDYanit(@SerializedName("succes") @Expose var succes: Int,
                     @SerializedName("message") @Expose var message: String)