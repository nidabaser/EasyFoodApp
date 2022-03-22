package com.example.easyfoodapp.retrofit

import com.example.easyfoodapp.entity.CRUDYanit
import com.example.easyfoodapp.entity.SepetYemeklerYanit
import com.example.easyfoodapp.entity.YemeklerYanit
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler(): Call<YemeklerYanit>


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteYemekEkle(@Field("sepet_yemek_id") yemek_id: Int,
                        @Field("yemek_adi") yemek_adi: String,
                        @Field("yemek_resim_adi") yemek_resim_adi: String,
                        @Field("yemek_fiyat") yemek_fiyat: Int,
                        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                        @Field("kullanici_adi") kullanici_adi: String): Call<CRUDYanit>


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepettekiYemekler(@Field("kullanici_adi") kullanici_adi: String): Call<SepetYemeklerYanit>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                         @Field("kullanici_adi") kullanici_adi: String): Call<CRUDYanit>
}