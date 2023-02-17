package com.example.tvshows

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TVDao {
    @Query("Select * from rss_table")
    fun getAllRss(): RssEntity?

    @Insert
    fun insertRss(rssEntity: RssEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun syncRss(rssEntity: RssEntity?)

    @Query("select * from TV_TABLE")
    fun getAllNews(): List<TV?>?

    @Insert
    fun insertNews(tv: TV?)

    @Query("select * from tv_table where parentId IN (:id)")
    fun getNews(id: Int): LiveData<List<TV>>?

    @Query("Select * from rss_table")
    fun getAvailableRss(): LiveData<List<RssEntity>>?
}