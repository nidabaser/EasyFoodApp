package com.example.easyfoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfoodapp.databinding.CardDesignBinding
import com.example.easyfoodapp.entity.Yemekler
import com.example.easyfoodapp.fragment.HomeFragmentDirections
import com.squareup.picasso.Picasso

class FoodsAdapter(var mContext:Context,
                   var yemeklerListe:List<Yemekler>) : RecyclerView.Adapter<FoodsAdapter.CardYemeklerTutucu>() {

    inner class CardYemeklerTutucu(yemeklerCardDesignBinding:CardDesignBinding) :
        RecyclerView.ViewHolder(yemeklerCardDesignBinding.root) {
        var yemeklerCardDesignBinding:CardDesignBinding

        init {
            this.yemeklerCardDesignBinding = yemeklerCardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardYemeklerTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardDesignBinding.inflate(layoutInflater, parent, false)
        return CardYemeklerTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardYemeklerTutucu, position: Int) {
        val yemek = yemeklerListe.get(position)
        val t = holder.yemeklerCardDesignBinding
        t.yemekNesne = yemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.ivFoodPng)

        t.cardViewFood.setOnClickListener {
            val gecis = HomeFragmentDirections.actHomeToDetail(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListe.size
    }
}