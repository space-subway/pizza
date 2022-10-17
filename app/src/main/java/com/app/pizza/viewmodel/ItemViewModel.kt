package com.app.pizza.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.app.pizza.api.ApiHelper
import com.app.pizza.api.RetrofitBuilder
import com.app.pizza.db.AppDatabase
import com.app.pizza.db.ItemDao
import com.app.pizza.repository.ItemRepository
import com.app.pizza.utils.Resource
import kotlinx.coroutines.Dispatchers

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val DEFAULT_ERR_MESSAGE = "Error Occured!"
    }

    private val repository: ItemRepository = ItemRepository( ApiHelper( RetrofitBuilder.apiService) )
    private val itemDao: ItemDao = AppDatabase.getDatabase(application).itemsDao()

    fun getItems() = liveData (Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val items = itemDao.allItems
        if(items.isNotEmpty()) emit(Resource.localSuccess(data = items))
        try {
            val received = repository.getItems()
            //Compare two lists
            if( !isEqual(received.sortedBy { it.id }, items.sortedBy { it.id }) ){
                itemDao.deleteAll()
                received.forEach { item ->  itemDao.insert( item ) }
                emit(Resource.remoteSuccess(data = received))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = items, message = exception.message ?: DEFAULT_ERR_MESSAGE))
        }
    }

    fun<T> isEqual(first: List<T>, second: List<T>): Boolean {

        if (first.size != second.size) {
            return false
        }

        return first.zip(second).all { (x, y) -> x == y }
    }

}