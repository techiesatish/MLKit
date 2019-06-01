package net.techiesatishktest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.techiesatishktest.db.dao.CodesDao
import net.techiesatishktest.db.entity.Codes

/**
 * Created by Satish on 31/05/2019 9:15 PM.
 */
@Database(entities = [Codes::class], version = 1)
abstract class CodesDatabase : RoomDatabase() {

    abstract fun codesDao(): CodesDao

//    companion object {
//        private var INSTANCE: CodesDatabase? = null
//
//        fun getDataBase(context: Context): CodesDatabase {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.applicationContext, CodesDatabase::class.java, "code_db")
//                    .allowMainThreadQueries().build()
//            }
//            return INSTANCE as CodesDatabase
//        }
//
//
//    }

    companion object {
        var INSTANCE: CodesDatabase? = null

        fun getAppDataBase(context: Context): CodesDatabase? {
            if (INSTANCE == null) {
                synchronized(CodesDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, CodesDatabase::class.java, "codes_db").build()
                }
            }
            return INSTANCE
        }

    }

    fun destroyInstance() {
        INSTANCE = null
    }

}