package com.example.arviewproject.navigator

import androidx.fragment.app.Fragment

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {
    fun showNewScreen(f:Fragment)
}