package com.example.tvshows

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = DatabaseConstant.TV_DB_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = RssEntity::class,
        parentColumns = arrayOf("rssId"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
class TV : Parcelable {
    constructor(
        parentId: Int,
        newsTitle: String?,
        newsDescription: String?,
        newsImage: String?,
        newsLink: String?,
        lastUpdate: Long
    ) {
        this.parentId = parentId
        this.title = newsTitle
        this.description = newsDescription
        this.image = newsImage
        this.link = newsLink
        this.lastUpdate = lastUpdate
    }

    @PrimaryKey(autoGenerate = true)
    var newsId = 0
    var parentId: Int

    @ColumnInfo(name = DatabaseConstant.TV_DB_TITLE_COLUMN)
    var title: String?

    @ColumnInfo(name = DatabaseConstant.TV_DB_DESCRIPTION_COLUMN)
    var description: String?

    @ColumnInfo(name = DatabaseConstant.TV_DB_IMAGE_COLUMN)
    var image: String?

    @ColumnInfo(name = DatabaseConstant.TV_DB_LINK_COLUMN)
    var link: String?

    @ColumnInfo(name = DatabaseConstant.TV_DB_LAST_UPDATE_COLUMN)
    var lastUpdate: Long

    protected constructor(`in`: Parcel) {
        parentId = `in`.readInt()
        title = `in`.readString()
        description = `in`.readString()
        image = `in`.readString()
        link = `in`.readString()
        lastUpdate = `in`.readLong()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(parentId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(link)
        parcel.writeLong(lastUpdate)
    }

    companion object CREATOR : Creator<TV> {
        override fun createFromParcel(parcel: Parcel): TV {
            return TV(parcel)
        }

        override fun newArray(size: Int): Array<TV?> {
            return arrayOfNulls(size)
        }
    }


}