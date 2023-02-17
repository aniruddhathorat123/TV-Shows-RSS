package com.example.tvshows.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tvshows.*
import java.util.concurrent.Executors

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val executorService = Executors.newFixedThreadPool(4)
    private var newsRepository: TVRepository = TVRepository(application, executorService)
    private var mAllNews: LiveData<List<TV>>? = null
    private var rssEntities: LiveData<List<RssEntity>>? = null
    private var parentRssId = -1

    private var db: TVDatabase? = TVDatabase.getInstance(application.applicationContext)
    private var dao: TVDao? = db?.tvDao()

    fun getAllShows(parentRssId: Int): LiveData<List<TV>>? {
        if (this.parentRssId != parentRssId) {
            this.parentRssId = parentRssId
            updateShowsList()
        }
        return mAllNews
    }

    fun getAllAvailableRss(): LiveData<List<RssEntity>>? {
        updateAvailableRssList()
        return rssEntities
    }

    fun updateAvailableRssList() {
        rssEntities = newsRepository.getAvailableRss()
    }

    private fun updateShowsList() {
        mAllNews = newsRepository.getNews(parentRssId)
    }


}