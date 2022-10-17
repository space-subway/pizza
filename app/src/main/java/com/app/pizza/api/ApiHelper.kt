package com.app.pizza.api

class ApiHelper( private val apiService: ApiService ) {

    suspend fun getItems() = apiService.getItems()

}