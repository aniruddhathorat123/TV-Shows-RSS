package com.example.tvshows

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.Executor

class TVRepository// todo
    (application: Application?, executor: Executor?) {
    private var TVDao: TVDao? = null
    private var executor: Executor? = null
    private var allNews: LiveData<List<TV>>? = null
    private var allRss: LiveData<List<RssEntity>>? = null


    init {
        if (application != null) {
            val db: TVDatabase? = TVDatabase.getInstance(application.applicationContext)
            this.executor = executor
            TVDao = db?.tvDao()
        } else {
            // todo
        }
    }

    fun getNews(parentRssId: Int): LiveData<List<TV>>? {
        allNews = TVDao?.getNews(parentRssId)
        return allNews
    }

    fun syncRss(rssEntity: RssEntity?) {
        TVDao?.syncRss(rssEntity)
    }

    fun getAvailableRss(): LiveData<List<RssEntity>>? {
        allRss = TVDao?.getAvailableRss()
        return allRss
    }
}