package com.example.wordappusingartcom.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.wordappusingartcom.WordDb
import com.example.wordappusingartcom.modal.myword

class MainViewModel(application: Application) :AndroidViewModel(application){

    val wordsDao= WordDb.getDatabase(application).dao()

    fun getAllWords() = wordsDao.getWords()

    fun insertWord(wor:myword) = wordsDao.insertWord(wor)

}
