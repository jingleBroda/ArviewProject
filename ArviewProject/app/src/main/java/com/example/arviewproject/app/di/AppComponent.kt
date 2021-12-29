package com.example.arviewproject.app.di

import com.example.arviewproject.app.App
import com.example.arviewproject.app.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    RetrofitModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    FragmentModule::class,
    RoomModule::class
])

@Singleton
interface AppComponent: AndroidInjector<App> {

    override fun inject(app: App)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(app: App):Builder

        fun build():AppComponent
    }

}