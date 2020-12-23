package com.google.developers.teacup.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tea::class], version = 1, exportSchema = false)
abstract class TeaDatabase : RoomDatabase() {

    abstract fun teaDao(): TeaDao

    companion object {

        @Volatile
        private var instance: TeaDatabase? = null

        /**
         * Returns an instance of Room Database
         *
         * @param context application context
         * @return The singleton TeaDatabase
         */
        fun getInstance(context: Context): TeaDatabase {
            return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(context, TeaDatabase::class.java,"tea.db")
                    .build()
            }

        }
    }
}
