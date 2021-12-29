package com.example.arviewproject.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arviewproject.app.di.module.utils.ViewModelFactory
import com.example.arviewproject.app.di.module.utils.ViewModelKey
import com.example.arviewproject.presentation.fragment.FirstFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstFragmentViewModel::class)
    internal abstract fun bindBottomSheetViewModel(firstFragmentViewModel: FirstFragmentViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}