package com.example.easyfoodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyfoodapp.R
import com.example.easyfoodapp.adapter.BasketFoodsAdapter
import com.example.easyfoodapp.databinding.FragmentBasketBinding
import com.example.easyfoodapp.entity.SepetYemekler
import com.example.easyfoodapp.viewmodel.BasketFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class BasketFragment : Fragment() {
    private lateinit var tasarim: FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentViewModel
    private lateinit var adapter: BasketFoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        tasarim.fragmentBasket = this

        tasarim.imageViewHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actBasketToHome)
        }

        tasarim.textViewEmptyBasket.visibility = View.INVISIBLE

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) {
                sepetYemeklerListesi -> var toplamSonuc = 0
                sepetYemeklerListesi.map { toplamSonuc += it.yemek_fiyat * it.yemek_siparis_adet }
                tasarim.tvTopPrice.text = toplamSonuc.toString() + " ₺"

            if(viewModel.sepetYemeklerListesi.value.isNullOrEmpty()){
                tasarim.textViewEmptyBasket.visibility = View.VISIBLE
            }

            adapter = BasketFoodsAdapter(requireContext(), sepetYemeklerListesi, viewModel)
            tasarim.basketFoodsAdapter = adapter
        }



        tasarim.btnOkay.setOnClickListener {
            if (viewModel.sepetYemeklerListesi.value.isNullOrEmpty()){
                Snackbar.make(it,"Lütfen sepete en az 1 adet ürün ekleyin",Snackbar.LENGTH_SHORT).show()
            }else{
                Navigation.findNavController(it).navigate(R.id.actBasketToOkay)
            }
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
}