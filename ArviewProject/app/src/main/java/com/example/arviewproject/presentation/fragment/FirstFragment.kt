package com.example.arviewproject.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.arviewproject.R
import com.example.arviewproject.app.di.module.utils.ViewModelFactory
import com.example.arviewproject.databinding.FragmentFirstBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FirstFragment : DaggerFragment() {

    private lateinit var binding:FragmentFirstBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel:FirstFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)

        binding = FragmentFirstBinding.bind(root)

       // viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[FirstFragmentViewModel::class.java]


        return root
    }

    override fun onStart() {
        super.onStart()

        viewModel.makeAPI()
    }


}