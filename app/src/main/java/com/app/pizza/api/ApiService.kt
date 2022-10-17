package com.app.pizza.api

import com.app.pizza.model.Item
import retrofit2.http.GET

interface ApiService {

    @GET("item/list")
    suspend fun getItems(): List<Item>

}