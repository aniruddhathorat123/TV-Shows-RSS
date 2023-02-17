package com.example.tvshows

object DatabaseConstant {
    const val TV_DB_VERSION = 1
    const val TV_DB_THREADS = 4
    const val TV_DB_NAME = "tv_database"

    const val TV_DB_TABLE_NAME = "tv_table"
    const val TV_DB_TITLE_COLUMN = "tv_title"
    const val TV_DB_DESCRIPTION_COLUMN = "tv_description"
    const val TV_DB_IMAGE_COLUMN = "tv_image"
    const val TV_DB_LINK_COLUMN = "tv_link"
    const val TV_DB_LAST_UPDATE_COLUMN = "last_update"

    const val TV_DB_RSS_TABLE_NAME = "rss_table"
    const val TV_DB_RSS_NAME_COLUMN = "rss_name"
    const val TV_DB_RSS_IMAGE_COLUMN = "rss_image"
    const val TV_DB_RSS_SOURCE_COLUMN = "rss_source"
    const val TV_DB_RSS_IS_AVAIL_COLUMN = "is_rss_available"

    const val TV_DB_SOURCE_LINK = "https://api.tvmaze.com/shows?page=1"

}