package com.example.easyfoodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class YemeklerYanit(
    @SerializedName("yemekler")
    @Expose var yemekler: List<Yemekler>,
    @SerializedName("success")
    @Expose var success: Int) {
}