package com.example.arviewproject.presentation.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.arviewprojectdomain.domain.usecase.MakeApiUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(val makeApiUseCase: MakeApiUseCase):ViewModel() {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun makeAPI(){
        val zaprosMakeApi = makeApiUseCase.doIt()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                  Log.d("APIzapr", it.toString())
            },{
                Log.d("APIzaprTroble", it.toString())
            })

        compositeDisposable.add(zaprosMakeApi)

    }

    fun cleareDisposable(){
        compositeDisposable.clear()
    }

}