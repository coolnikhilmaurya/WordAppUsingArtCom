package com.example.wordappusingartcom

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.wordappusingartcom.modal.myword


@Dao
interface WordDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(wor:myword)

    @Query("select * from myword order by id desc")
    fun getWords():LiveData<List<myword>>

    @Delete
    fun deleteWord(wor: myword)


}