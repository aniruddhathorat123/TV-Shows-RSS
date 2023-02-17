package com.example.tvshows

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [TV::class, RssEntity::class], version = DatabaseConstant.TV_DB_VERSION)
public abstract class TVDatabase : RoomDatabase() {
    public abstract fun tvDao(): TVDao

    companion object {
        var instance: TVDatabase? = null

        @Synchronized
         public fun getInstance(context: Context): TVDatabase? {
            if (TVDatabase.instance == null) {
                TVDatabase.instance = Room.databaseBuilder(
                    context.applicationContext,
                    TVDatabase::class.java, DatabaseConstant.TV_DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(TVDatabase.initiateTVDB)
                    .build()
            }
            return TVDatabase.instance
        }

        val initiateTVDB: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                if (instance != null) {
                    TVDatabase.InitialiseDBAsyncTask(TVDatabase.instance)
                        .execute()
                }
            }
        }
    }
    //var instance: TVDatabase? = null
    val databaseWriteExecutor =
        Executors.newFixedThreadPool(DatabaseConstant.TV_DB_THREADS)

    // Initiate the DB with initial RSS data.
    private class InitialiseDBAsyncTask(db: TVDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val TVDao: TVDao

        init {
            TVDao = db?.tvDao()!!
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            //TODO("Not yet implemented")
            val toiEntity = RssEntity()
            toiEntity.rssId = 1
            toiEntity.rssName = "TV database"
            toiEntity.rssImage = ""
            toiEntity.rssSource = DatabaseConstant.TV_DB_SOURCE_LINK
            toiEntity.isRssAvail = true
            TVDao.insertRss(toiEntity)
            return null
        }
    }
}