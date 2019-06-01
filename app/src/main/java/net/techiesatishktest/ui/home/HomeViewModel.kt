package net.techiesatishktest.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.techiesatishktest.db.entity.Codes
import net.techiesatishktest.repo.Code_Repo

/**
 * Created by Satish on 25/05/2019 11:01 AM.
 */
class HomeViewModel(mApplication: Application) : AndroidViewModel(mApplication){

    private var repository: Code_Repo =
        Code_Repo(mApplication)

    var allCodes: LiveData<List<Codes>> = repository.getListCodes()

    fun insert(mCodes: Codes) {
        repository.insert(mCodes)
    }



}