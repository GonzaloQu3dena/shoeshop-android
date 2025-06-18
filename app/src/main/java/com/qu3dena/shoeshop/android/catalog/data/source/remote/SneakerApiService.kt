package com.qu3dena.shoeshop.android.catalog.data.source.remote

import com.qu3dena.shoeshop.android.catalog.data.entities.SneakerDto
import retrofit2.http.GET

interface SneakerApiService {
    @GET("shoes")
    suspend fun getSneakers(): List<SneakerDto>
}