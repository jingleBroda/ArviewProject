package com.example.arviewproject.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.arviewproject.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FirstFragmentViewModel(application: Application):AndroidViewModel(application) {

    private val retrofiteServise = (application as App).getRetrofiteService()
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
   // private val bearerToken = "7hq7mo22fniilrcud3dwml32mxchj9"
   // private val clientId = "ahuoi1tl0qmqbyi8jo8nitbmuaad7w"

    fun makeAPI(){
        val zapros1 = retrofiteServise.getTopGames(
            1,
            1
        )
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                  Log.d("APIzapr", it.toString())
            },{
                Log.d("APIzaprTroble", it.toString())
            })

        compositeDisposable.add(zapros1)

    }

}