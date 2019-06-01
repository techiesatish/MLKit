package net.techiesatishktest.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Satish on 31/05/2019 9:06 PM.
 */
@Entity(tableName = "codes_table")
data class Codes(

    var mTitle: String,

    var mTimeStamps: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}