package com.app.pizza.utils

import androidx.room.TypeConverter
import com.app.pizza.model.ItemCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromItemCategory( value: ItemCategory? ): String? {
            val type = object: TypeToken<ItemCategory>(){}.type
            return Gson().toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toItemCategory( value: String? ): ItemCategory? {
            val type = object: TypeToken<ItemCategory>(){}.type
            return Gson().fromJson(value, type)
        }

    }
}