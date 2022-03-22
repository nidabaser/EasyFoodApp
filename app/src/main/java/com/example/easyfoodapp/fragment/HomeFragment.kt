package com.example.easyfoodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.easyfoodapp.R
import com.example.easyfoodapp.adapter.FoodsAdapter
import com.example.easyfoodapp.databinding.FragmentHomeBinding
import com.example.easyfoodapp.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private lateinit var tasarim: FragmentHomeBinding
    private lateinit var adapter: FoodsAdapter
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
                yemeklerListesi -> adapter = FoodsAdapter(requireContext(), yemeklerListesi)
                tasarim.foodsAdapter = adapter
        }

        tasarim.imageViewBasket.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actHomeToBasket)
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

}