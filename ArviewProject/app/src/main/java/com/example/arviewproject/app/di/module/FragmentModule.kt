package com.example.arviewproject.app.di.module

import com.example.arviewproject.presentation.fragment.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun firstFragment(): FirstFragment
}