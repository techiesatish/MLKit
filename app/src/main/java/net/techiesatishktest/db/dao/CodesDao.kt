package net.techiesatishktest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.techiesatishktest.db.entity.Codes

/**
 * Created by Satish on 31/05/2019 9:06 PM.
 */
@Dao
interface CodesDao {

    @Insert
    fun insert(mCodes: Codes)

//    @Query("DELETE FROM notes_table")
//    fun deleteAllNotes()

    @Query("SELECT * FROM codes_table ")
    fun getAllCodes(): LiveData<List<Codes>>

}