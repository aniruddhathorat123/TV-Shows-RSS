package com.example.tvshows

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DatabaseConstant.TV_DB_RSS_TABLE_NAME)
class RssEntity : Parcelable {
    constructor() {}

    @PrimaryKey
    var rssId = 0

    @ColumnInfo(name = DatabaseConstant.TV_DB_RSS_NAME_COLUMN)
    var rssName: String? = null

    @ColumnInfo(name = DatabaseConstant.TV_DB_RSS_IMAGE_COLUMN)
    var rssImage: String? = null

    @ColumnInfo(name = DatabaseConstant.TV_DB_RSS_SOURCE_COLUMN)
    var rssSource: String? = null

    @ColumnInfo(name = DatabaseConstant.TV_DB_RSS_IS_AVAIL_COLUMN)
    var isRssAvail = false

    protected constructor(`in`: Parcel) {
        rssId = `in`.readInt()
        rssName = `in`.readString()
        rssImage = `in`.readString()
        rssSource = `in`.readString()
        isRssAvail = `in`.readByte().toInt() != 0
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(rssId)
        parcel.writeString(rssName)
        parcel.writeString(rssImage)
        parcel.writeString(rssSource)
        parcel.writeByte((if (isRssAvail) 1 else 0).toByte())
    }

    companion object CREATOR : Creator<RssEntity> {
        override fun createFromParcel(parcel: Parcel): RssEntity {
            return RssEntity(parcel)
        }

        override fun newArray(size: Int): Array<RssEntity?> {
            return arrayOfNulls(size)
        }
    }


}