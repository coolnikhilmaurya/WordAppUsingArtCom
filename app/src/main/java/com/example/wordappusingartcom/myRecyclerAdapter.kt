package com.example.wordappusingartcom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wordappusingartcom.activity.InsertActivity
import com.example.wordappusingartcom.modal.myword
import kotlinx.android.synthetic.main.my_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class myRecyclerAdapter(var list:List<myword>,var ctx:Context) : RecyclerView.Adapter<myRecyclerAdapter.myViewHolder>() {
    val wordsDao= WordDb.getDatabase(ctx).dao()
    val intent= Intent(ctx,InsertActivity::class.java)
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): myViewHolder {
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.my_item,viewGroup,false)
        return myViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.tvWord.text=list[position].word
        holder.tvMeaning.text=list[position].meaning
        val sdf = SimpleDateFormat("EEE, dd MMM HH:mm:ss")
        holder.tvDate.text=sdf.format(list[position].date)

        holder.itemView.setOnClickListener {
            intent.putExtra("id",list[position].id)
            intent.putExtra("word",list[position].word)
            intent.putExtra("meaning",list[position].meaning)
            ctx.startActivity(intent)
        }

        holder.btnDel.setOnClickListener {
            wordsDao.deleteWord(list[position])
        }
    }

    inner class myViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val tvWord=view.findViewById<TextView>(R.id.tvWord)
        val tvMeaning=view.findViewById<TextView>(R.id.tvMeaning)
        val tvDate:TextView=view.findViewById(R.id.tvDate)

        val btnDel=view.btnItemDelete
    }
}