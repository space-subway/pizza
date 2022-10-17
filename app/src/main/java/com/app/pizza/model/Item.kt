package com.app.pizza.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.app.pizza.model.Item.CREATOR.ID
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "item",
    indices = [Index(value = [ID], unique = true)]
)
data class Item(
    @SerializedName(ID) @PrimaryKey @ColumnInfo(name = ID) var id: String,
    @SerializedName(TITLE) @ColumnInfo(name = TITLE) var title: String?,
    @SerializedName(DESCRIPTION) @ColumnInfo(name = DESCRIPTION) var description: String?,
    @SerializedName(PRICE) @ColumnInfo(name = PRICE) var price: Double,
    @SerializedName(CATEGORY) @ColumnInfo(name = CATEGORY) var category: ItemCategory?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readParcelable(ItemCategory.javaClass.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeDouble(price.toDouble())
        parcel.writeParcelable(category, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (price != other.price) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + price.hashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Item> {

        const val TABLE_NAME        = "item"
        const val ID                = "id"
        const val TITLE             = "title"
        const val DESCRIPTION       = "description"
        const val PRICE             = "price"
        const val CATEGORY          = "category"

        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}