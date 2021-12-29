package com.example.arviewproject.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arviewproject.R
import com.example.arviewproject.app.di.module.utils.ViewModelFactory
import com.example.arviewproject.databinding.FragmentFirstBinding
import com.example.arviewproject.presentation.adapter.TopGameAdapterRecView
import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import android.widget.Toast
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.PaginationTwitch
import java.io.IOException


class FirstFragment : DaggerFragment() {

    private lateinit var binding:FragmentFirstBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel:FirstFragmentViewModel
    private lateinit var adapter:TopGameAdapterRecView
    private lateinit var dinamicScrollRecView:RecyclerView.OnScrollListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)
        binding = FragmentFirstBinding.bind(root)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[FirstFragmentViewModel::class.java]


        binding.reviewButton.setOnClickListener{
            val bottomSheetFragment = BottomSheetFragment()

            bottomSheetFragment.show(requireActivity().supportFragmentManager, "tag")
        }


        return root
    }

    override fun onStart() {
        super.onStart()

        if (isReallyOnline()){
            binding.internetStatusText.setText(R.string.internetConnectTrue)
            createScrollListner()

            viewModel.makeAPI()

            viewModel.getDataStartApi { dataApi->
                adapter = TopGameAdapterRecView(requireContext(),dataApi)
                binding.topGameRecView.layoutManager = LinearLayoutManager(activity)
                binding.topGameRecView.hasFixedSize()
                binding.topGameRecView.adapter = adapter

                for(i in 0 until adapter.getDataModel().data.size){
                    viewModel.insetItemInDB(adapter.getDataModel().data[i])
                }

            }

        }
        else{
            binding.internetStatusText.setText(R.string.internetConnectFalse)
            viewModel.makeDbAllTopGamesTwitch()
            viewModel.getDataInDB { data->
                val emulatorPagination = PaginationTwitch("emulator")
                val emulatorRetrofit = ModelRetrofit((data as ArrayList<DataTwitch>), emulatorPagination)

                if(data.isEmpty()){
                    Toast.makeText(requireContext(),
                        "На устройстве нет сохраненных игр! Пожалуйста, подключитесь к интернету!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else {
                    adapter = TopGameAdapterRecView(requireContext(), emulatorRetrofit)
                    binding.topGameRecView.layoutManager = LinearLayoutManager(activity)
                    binding.topGameRecView.hasFixedSize()
                    binding.topGameRecView.adapter = adapter
                }

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearCompositeDisposableMakeAPI()
        viewModel.clearCompositeDisposableNewGame()
        viewModel.cleareCompositeDisposableDB()
    }


    //проверка интернет соединения
    private fun isReallyOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }

    private fun createScrollListner(){
        dinamicScrollRecView = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount: Int = (recyclerView.layoutManager as LinearLayoutManager).childCount
                val totalItemCount: Int =  (recyclerView.layoutManager as LinearLayoutManager).itemCount
                val firstVisibleItems: Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ( (visibleItemCount+firstVisibleItems) >= totalItemCount) {

                    viewModel.getNewTopGame(
                        (recyclerView.adapter as TopGameAdapterRecView).getDataModel().pagination.cursor,
                        5
                    )

                    binding.progressNewGameDownload.visibility = View.VISIBLE

                    viewModel.getDataNewTopGame { newGame ->
                        adapter.updateData(newGame)
                        binding.progressNewGameDownload.visibility = View.GONE
                        viewModel.clearCompositeDisposableNewGame()

                        for(i in 0 until adapter.getDataModel().data.size){
                            viewModel.insetItemInDB(adapter.getDataModel().data[i])
                        }
                    }

                }
            }
        }

        binding.topGameRecView.addOnScrollListener(dinamicScrollRecView)
    }

}