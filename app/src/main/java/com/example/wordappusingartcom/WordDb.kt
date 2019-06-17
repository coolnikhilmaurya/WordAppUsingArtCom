package com.example.wordappusingartcom

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.example.wordappusingartcom.modal.myword


@Database(entities = arrayOf(myword::class) ,version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class WordDb : RoomDatabase() {
    abstract fun dao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDb? = null

        fun getDatabase(context: Context): WordDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(mydb: SupportSQLiteDatabase) {
                    // do migrations here
                    mydb.execSQL("ALTER TABLE myword ADD COLUMN date INTEGER")
                }
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDb::class.java,
                    util.DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}