package com.example.arviewprojectdomain.domain.model

import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.PaginationTwitch

data class ModelRetrofit(
    var data:ArrayList<DataTwitch>,
    var pagination: PaginationTwitch
)