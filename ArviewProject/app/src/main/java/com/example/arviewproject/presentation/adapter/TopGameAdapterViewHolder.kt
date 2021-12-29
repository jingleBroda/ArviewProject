package com.example.arviewproject.presentation.adapter

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arviewproject.R
import com.example.arviewproject.databinding.TopGameRecViewRowBinding
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch

class TopGameAdapterViewHolder(view: View): RecyclerView.ViewHolder(view){
   private val binding = TopGameRecViewRowBinding.bind(view)

   fun bind(dataInside: DataTwitch){
       //эмуляция данных о зрителях и трансляциях без сортировки и тд.
       binding.numberViewer.setText(R.string.numberOfViewers)
       binding.numberViewer.text = (dataInside.id/1000).toString() +" "+binding.numberViewer.text
       binding.numberBroadcast.setText(R.string.numberOfBroadcasts)
       binding.numberBroadcast.text = (dataInside.id/100).toString()+" "+binding.numberBroadcast.text

       binding.nameGame.text = dataInside.name


       convertUrlGame(dataInside.box_art_url)

   }

    private fun convertUrlGame(url:String){

        var urlCopy = url.replace(
            "{width}",
            "1000"
        )

        urlCopy = urlCopy.replace(
            "{height}",
            "1000"
        )

        Glide
            .with(binding.previewGameImg)
            .load(urlCopy)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(binding.previewGameImg);

    }
}