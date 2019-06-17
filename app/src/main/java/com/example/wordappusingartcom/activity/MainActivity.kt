package com.example.wordappusingartcom.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.wordappusingartcom.R
import com.example.wordappusingartcom.myRecyclerAdapter
import com.example.wordappusingartcom.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myViewModel: MainViewModel
    var reqCode=2
    var bud:Bundle = Bundle()
    lateinit var recAdapter: myRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.mytoolbar))
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

        myViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        myFab.setOnClickListener{
            startActivityForResult(Intent(this, InsertActivity::class.java),reqCode,bud)
        }
        myRecyclerView.layoutManager=LinearLayoutManager(this)
        myViewModel.getAllWords().observe(this, Observer {
            it?.let {   WordList->
                recAdapter= myRecyclerAdapter(WordList,this)
                myRecyclerView.adapter=recAdapter
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode==0 && requestCode==reqCode){
            Toast.makeText(this,"Got text - ${data?.getStringExtra("InsertText")}",Toast.LENGTH_LONG).show()
        }
    }

}
