package com.example.easyfoodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfoodapp.entity.Yemekler
import com.example.easyfoodapp.repo.FoodsDaoRepository

class HomeFragmentViewModel : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private val fdao = FoodsDaoRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = fdao.yemekleriGetir()
    }

    fun yemekleriYukle() {
        fdao.tumYemekleriAl()
    }
}