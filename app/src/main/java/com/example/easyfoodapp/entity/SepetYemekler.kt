package com.example.easyfoodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SepetYemekler(@SerializedName("sepet_yemek_id")
                         @Expose var sepet_yemek_id: Int,
                         @SerializedName("yemek_adi")
                         @Expose var yemek_adi: String,
                         @SerializedName("yemek_resim_adi")
                         @Expose var yemek_resim_adi: String,
                         @SerializedName("yemek_fiyat")
                         @Expose var yemek_fiyat: Int,
                         @SerializedName("yemek_siparis_adet")
                         @Expose var yemek_siparis_adet: Int,
                         @SerializedName("kullanici_adi")
                         @Expose var kullanici_adi: String = "nidabaser") : Serializable {
}