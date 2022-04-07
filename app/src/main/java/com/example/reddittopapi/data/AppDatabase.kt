package com.example.reddittopapi.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reddittopapi.data.dao.DaoPublication
import com.example.reddittopapi.data.entity.PublicationTable

@Database(entities = [PublicationTable::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val getDao: DaoPublication
}