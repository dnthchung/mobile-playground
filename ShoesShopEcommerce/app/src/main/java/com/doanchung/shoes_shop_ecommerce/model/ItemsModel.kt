package com.doanchung.shoes_shop_ecommerce.model

import android.os.Parcel
import android.os.Parcelable

/**
 * TODO("picUrl"),
 * TODO("size"),
 */

data class ItemsModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        /* TODO("picUrl"), */
        parcel.createStringArrayList() as ArrayList<String>,
        /* TODO("size"), */
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeInt(numberInCart)
    }

    companion object CREATOR : Parcelable.Creator<ItemsModel> {
        override fun createFromParcel(parcel: Parcel): ItemsModel {
            return ItemsModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsModel?> {
            return arrayOfNulls(size)
        }
    }

}
