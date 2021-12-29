package com.example.arviewproject.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arviewproject.R
import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch

class TopGameAdapterRecView(context: Context, dataInside: ModelRetrofit): RecyclerView.Adapter<TopGameAdapterViewHolder>() {

    private val contextAdapter = context
    private var arrayData = dataInside

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopGameAdapterViewHolder {
        val inflater = LayoutInflater.from(contextAdapter)
        return TopGameAdapterViewHolder(inflater.inflate(R.layout.top_game_rec_view_row, parent, false))
    }

    override fun onBindViewHolder(holder: TopGameAdapterViewHolder, position: Int) {
        val concretRow = arrayData.data[position]
        holder.bind(concretRow)
    }

    override fun getItemCount(): Int {
        return arrayData.data.size
    }


    fun getDataModel():ModelRetrofit{
        return arrayData
    }

    fun updateData(newData:ModelRetrofit){
        arrayData.data = (arrayData.data+newData.data) as ArrayList<DataTwitch>
        arrayData.pagination = newData.pagination

        notifyDataSetChanged()
    }

}