package com.example.easyfoodapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.easyfoodapp.databinding.FragmentFoodDetailBinding
import com.example.easyfoodapp.entity.*
import com.example.easyfoodapp.retrofit.ApiUtils
import com.example.easyfoodapp.retrofit.FoodsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsDaoRepository {
     var yemeklerListesi: MutableLiveData<List<Yemekler>>
     private var fdaoi: FoodsDaoInterface
     var sepetYemeklerListesi: MutableLiveData<List<SepetYemekler>>

    init {
        fdaoi = ApiUtils.getFoodsDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir(): MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun tumYemekleriAl() {
        fdaoi.tumYemekler().enqueue(object : Callback<YemeklerYanit> {
            override fun onResponse(call: Call<YemeklerYanit>, response: Response<YemeklerYanit>) {
                val liste = response.body()?.yemekler ?: emptyList()
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerYanit>, t: Throwable) {}
        })
    }

    fun sepetYemekleriGetir(): MutableLiveData<List<SepetYemekler>> {
        return sepetYemeklerListesi
    }

    fun tumSepetYemekleriAl(kullanici_adi: String) {
        fdaoi.sepettekiYemekler("nidabaser")
            .enqueue(object : Callback<SepetYemeklerYanit> {
                override fun onResponse(call: Call<SepetYemeklerYanit>, response: Response<SepetYemeklerYanit>) {
                    val liste = response.body()?.sepet_yemekler ?: emptyList()

                    sepetYemeklerListesi.value = liste
                }
                override fun onFailure(call: Call<SepetYemeklerYanit>, t: Throwable) {
                }
            })
    }

    fun sepeteEkle(sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        fdaoi.sepeteYemekEkle(sepet_yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object : Callback<CRUDYanit> {
            override fun onResponse(call: Call<CRUDYanit>, response: Response<CRUDYanit>) {
                try {
                    if(sepetYemeklerListesi.value != null && sepetYemeklerListesi.value!!.isNotEmpty()) {
                        tumSepetYemekleriAl(kullanici_adi)
                    }
                    tumSepetYemekleriAl(kullanici_adi)

                }catch (e: Exception){
                }
            }
            override fun onFailure(call: Call<CRUDYanit>, t: Throwable) {
            }
        })
    }

    fun sepettenYemekSilme(sepet_yemek_id: Int, kullanici_adi: String) {
        fdaoi.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDYanit> {
                override fun onResponse(call: Call<CRUDYanit>, response: Response<CRUDYanit>) {

                    try {
                        if(sepetYemeklerListesi.value!!.size == 1) {
                            sepetYemeklerListesi.value = emptyList()
                        }
                        tumSepetYemekleriAl(kullanici_adi)

                    }catch (e: Exception){
                    }

                }
                override fun onFailure(call: Call<CRUDYanit>, t: Throwable) {

                }
            })
    }


    private fun arttirAzalt(adet: Int, tasarim: FragmentFoodDetailBinding) {
        tasarim.textViewAdet.text = "$adet"
    }

    fun artir(tasarim: FragmentFoodDetailBinding) {
        arttirAzalt(tasarim.textViewAdet.text.toString().toInt() + 1, tasarim)
    }

    fun azalt(tasarim: FragmentFoodDetailBinding) {
        arttirAzalt(tasarim.textViewAdet.text.toString().toInt() - 1, tasarim)
        if (tasarim.textViewAdet.text.toString().toInt() < 2) {
            tasarim.textViewAdet.text = "1" }
    }

    fun btnAddMinus(tasarim: FragmentFoodDetailBinding) {
        tasarim.btnPlus .setOnClickListener { artir(tasarim) }
        tasarim.btnMinus.setOnClickListener { azalt(tasarim) }
    }
}