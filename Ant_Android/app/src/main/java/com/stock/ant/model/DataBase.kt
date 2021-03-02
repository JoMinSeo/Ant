package com.stock.ant.model

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ant::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao() : Dao

    companion object {
        private var INSTANCE : DataBase?= null

        fun getInstance(context: Context) : DataBase? {
            if (INSTANCE == null) {
                synchronized(Ant::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java, "Ant.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}