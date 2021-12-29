package com.example.arviewproject.presentation.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import com.example.arviewprojectdomain.domain.usecase.GetAllGamesInDBUseCase
import com.example.arviewprojectdomain.domain.usecase.GetNewItemTopGameUseCase
import com.example.arviewprojectdomain.domain.usecase.InsertGamesInDBUseCase
import com.example.arviewprojectdomain.domain.usecase.MakeApiUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(
    val makeApiUseCase: MakeApiUseCase,
    val getNewItemTopGameUseCase: GetNewItemTopGameUseCase,
    val getAllGamesInDBUseCase: GetAllGamesInDBUseCase,
    val insertGamesInDBUseCase: InsertGamesInDBUseCase
    ):ViewModel() {

    private val compositeDisposableMakeAPI : CompositeDisposable = CompositeDisposable()
    private val compositeDisposableNewGame : CompositeDisposable = CompositeDisposable()
    private val compositeDisposableDB : CompositeDisposable = CompositeDisposable()

    private var getDataStartApiLamda : ((ModelRetrofit) -> Unit)? = null
    private var getDataNewTopGameLamda : ((ModelRetrofit) -> Unit)? = null
    private var getDataInDBLamda : ((List<DataTwitch>) -> Unit)? = null

    fun makeAPI(){

        if(!compositeDisposableMakeAPI.isDisposed) {

            val zaprosMakeApi = makeApiUseCase.doIt()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->

                    //Log.d("APIzapr", data.toString())

                    getDataStartApiLamda?.invoke(data)

                }, {
                    Log.d("APIzaprTroble", it.toString())
                })

            compositeDisposableMakeAPI.add(zaprosMakeApi)
        }

    }

    fun getDataStartApi(code:((ModelRetrofit) -> Unit)?){
        getDataStartApiLamda = code
    }

    fun getNewTopGame(pagination:String, first:Int){

        if(!compositeDisposableNewGame.isDisposed) {

            val zaprosgetNewTopGame = getNewItemTopGameUseCase.doIt(pagination, first)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->

                   // Log.d("APIzaprNewGame", data.toString())

                    getDataNewTopGameLamda?.invoke(data)

                }, {
                    Log.d("APIzaprTrobleNewGame", it.toString())
                })

            compositeDisposableNewGame.add(zaprosgetNewTopGame)
        }
    }

    fun getDataNewTopGame(code:((ModelRetrofit) -> Unit)?){
        getDataNewTopGameLamda = code
    }

    fun clearCompositeDisposableMakeAPI(){
        compositeDisposableMakeAPI.clear()
    }

    fun clearCompositeDisposableNewGame(){
        compositeDisposableNewGame.clear()
    }


    fun makeDbAllTopGamesTwitch(){
        val zaprosInAllItemDb = getAllGamesInDBUseCase.doIt()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                getDataInDBLamda?.invoke(data)

            }, {
                Log.d("RoomError", it.toString())
            })

        compositeDisposableDB.add(zaprosInAllItemDb)
    }

    fun getDataInDB(code:(List<DataTwitch>) -> Unit){
        getDataInDBLamda = code
    }

    fun insetItemInDB(item:DataTwitch){
        val zaprosInInsertDB = insertGamesInDBUseCase.doIt(item)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("RoomInsert", "Success Insert")
            }, {
                Log.d("RoomError", it.toString())
            })

        compositeDisposableDB.add(zaprosInInsertDB)
    }

    fun cleareCompositeDisposableDB(){
        compositeDisposableDB.clear()
    }

}