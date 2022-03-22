package com.example.easyfoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfoodapp.databinding.CardBasketDesignBinding
import com.example.easyfoodapp.databinding.FragmentBasketBinding
import com.example.easyfoodapp.entity.SepetYemekler
import com.example.easyfoodapp.viewmodel.BasketFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class BasketFoodsAdapter(var mContext: Context,
                         var sepettekiYemeklerListesi: List<SepetYemekler>,
                         var viewModel: BasketFragmentViewModel)
    : RecyclerView.Adapter<BasketFoodsAdapter.CardBasketDesignHolder>() {

    inner class CardBasketDesignHolder(cardBasketDesignBinding: CardBasketDesignBinding) :
        RecyclerView.ViewHolder(cardBasketDesignBinding.root) {
        var sepetCardDesignBinding:CardBasketDesignBinding

        init {
            this.sepetCardDesignBinding = cardBasketDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBasketDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardBasketDesignBinding.inflate(layoutInflater, parent, false)
        return CardBasketDesignHolder(tasarim)
    }

    override fun onBindViewHolder(holder: CardBasketDesignHolder, position: Int) {
        val sepetyemek = sepettekiYemeklerListesi.get(position)
        val t = holder.sepetCardDesignBinding
        t.yemekSepetNesne = sepetyemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetyemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageView)

        t.imageButtonDelete.setOnClickListener {
            Snackbar.make(it, "${sepetyemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.yemekSil(sepetyemek.sepet_yemek_id, sepetyemek.kullanici_adi) }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return sepettekiYemeklerListesi.size
    }
}

