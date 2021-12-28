package com.example.arviewproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arviewproject.R
import com.example.arviewproject.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding:FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)

        binding = FragmentFirstBinding.bind(root)

        return root
    }


}