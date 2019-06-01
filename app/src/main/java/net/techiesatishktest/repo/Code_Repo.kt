package net.techiesatishktest.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import net.techiesatishktest.db.CodesDatabase
import net.techiesatishktest.db.entity.Codes

/**
 * Created by Satish on 31/05/2019 9:02 PM.
 */

class Code_Repo(mApplication: Application){
    var list: LiveData<List<Codes>>
    private val appDb: CodesDatabase

    init {
        appDb = CodesDatabase.getAppDataBase(mApplication.applicationContext)!!
        list = appDb.codesDao().getAllCodes()
    }

    fun getListCodes(): LiveData<List<Codes>> {
        return list
    }

    fun insert(contact: Codes) {
        addAsynTask(appDb).execute(contact)
    }


    class addAsynTask(db: CodesDatabase) : AsyncTask<Codes, Void, Void>() {
        private var contactDb = db
        override fun doInBackground(vararg params: Codes): Void? {
            contactDb.codesDao().insert(params[0])
            return null
        }

    }




}