package com.example.wordappusingartcom.modal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class myword(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var word:String ="",
    var meaning:String="",
    var date:Date = Date()
)

