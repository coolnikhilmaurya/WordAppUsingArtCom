package com.example.wordappusingartcom.activity

import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.wordappusingartcom.R
import com.example.wordappusingartcom.WordDb
import com.example.wordappusingartcom.modal.myword
import com.example.wordappusingartcom.util
import com.example.wordappusingartcom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_insert.*
import java.util.*

class InsertActivity : AppCompatActivity() {

    lateinit var myViewModel: MainViewModel
    var isUpdate=false
    var id=0
    lateinit var newword:myword

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        setSupportActionBar(findViewById(R.id.mytoolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        myViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        if(intent!=null){
            val b=intent.extras
            b?.let {
                tvWord.setText(it.getString("word"))
                tvMeaning.setText(it.getString("meaning"))
                isUpdate=true
                id=b.getInt("id")
            }

        }

        btnSubmit.setOnClickListener {
            if(tvWord.text.toString()=="" || tvMeaning.text.toString()=="") {
                Snackbar.make(it.rootView,"Please enter both felds",Snackbar.LENGTH_SHORT).show()
            }
            else {
                val inten = Intent()
                // insert into the db

                if(isUpdate){
                    newword = myword(id = id,word = tvWord.text.toString(), meaning = tvMeaning.text.toString())
                }
                else {
                    newword = myword(word = tvWord.text.toString(), meaning = tvMeaning.text.toString(),date = Calendar.getInstance().time)

                }
                myViewModel.insertWord(wor = newword)
                inten.putExtra("InsertText", tvWord.text.toString())
                setResult(0, inten)
                finish()
            }
        }
    }

    /*override fun onSupportNavigateUp(): Boolean {
         super.onSupportNavigateUp()
        finish()
        util.logit("Invoked onSupportNavigateUp")
        return true
    }*/
}
