package com.app.pizza.repository

import com.app.pizza.api.ApiHelper

class ItemRepository(private val apiHelper: ApiHelper) {

    suspend fun getItems() = apiHelper.getItems()

}