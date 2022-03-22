package com.example.easyfoodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.easyfoodapp.R
import com.example.easyfoodapp.databinding.FragmentOrderOkayBinding

class OrderOkayFragment : Fragment() {
    private lateinit var tasarim:FragmentOrderOkayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentOrderOkayBinding.inflate(inflater,container,false)


        tasarim.btnReturn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actReturnHome)
        }


        return tasarim.root
    }
}