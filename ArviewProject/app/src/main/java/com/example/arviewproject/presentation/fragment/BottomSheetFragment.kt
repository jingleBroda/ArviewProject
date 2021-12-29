package com.example.arviewproject.presentation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.example.arviewproject.R
import com.example.arviewproject.databinding.BottomSheetLayoutBinding
import com.example.arviewproject.presentation.navigator.navigator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment:BottomSheetDialogFragment() {
    private lateinit var binding:BottomSheetLayoutBinding
    private lateinit var editor:SharedPreferences.Editor
    private lateinit var preferences:SharedPreferences
    private val tokenFeedBack = "tokenFeedBack"
    private val tokenRatingBar = "tokenRatingBar"

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.bottom_sheet_layout, container)
        binding = BottomSheetLayoutBinding.bind(root)

        preferences = requireContext().getSharedPreferences("Feedback", Context.MODE_PRIVATE)
        editor = preferences.edit()

        binding.feedBackText.setText(preferences.getString(tokenFeedBack, ""))
        binding.ratingBar.rating = preferences.getFloat(tokenRatingBar, 0F)

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }



        return root
    }

    override fun onStart() {
        super.onStart()

        binding.saveButton.setOnClickListener{

            if( binding.feedBackText.text.toString() == ""){
                Toast.makeText(requireContext(), "Поле с текстом пустое!", Toast.LENGTH_SHORT).show()
            }
            else {

                Toast.makeText(requireContext(), "Спасибо за отзыв!", Toast.LENGTH_SHORT).show()

                editor.putString(tokenFeedBack, binding.feedBackText.text.toString())
                editor.commit()

                editor.putFloat(tokenRatingBar, binding.ratingBar.rating)
                editor.commit()

                this.dismiss()
            }

        }

    }
}