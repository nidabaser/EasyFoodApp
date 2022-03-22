package com.example.easyfoodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.easyfoodapp.R
import com.example.easyfoodapp.databinding.FragmentFoodDetailBinding
import com.example.easyfoodapp.entity.Yemekler
import com.example.easyfoodapp.viewmodel.FoodDetailFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class FoodDetailFragment : Fragment() {
    private lateinit var tasarim: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailFragmentViewModel
    val kullanici_adi = "nidabaser"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)

        val bundle: FoodDetailFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        tasarim.foodDetailFragment = this
        tasarim.yemekNesne = gelenYemek
        tasarim.tvFoodName.text = gelenYemek.yemek_adi
        tasarim.tvFoodPrice.text = gelenYemek.yemek_fiyat.toString() + " ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(tasarim.imageViewFood)

        viewModel.buttonEkleCikar(tasarim)

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun btnAddToBasket(yemekNesne: Yemekler, adet: String, it: View) {
        val snackbarOnay = Snackbar.make(it,"${adet} adet ${yemekNesne.yemek_adi} başarıyla eklendi...",Snackbar.LENGTH_SHORT)
            viewModel.sepetEkle(
                yemekNesne.yemek_id,
                yemekNesne.yemek_adi,
                yemekNesne.yemek_resim_adi,
                yemekNesne.yemek_fiyat,
                adet.toInt(),
                kullanici_adi)
        snackbarOnay.show()
        Navigation.findNavController(it).navigate(R.id.actDetailToBasket)
    }
}
