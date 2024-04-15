package com.example.androidassessmentbynasim

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidassessmentbynasim.databinding.ActivityDetailBinding
import com.google.gson.Gson
import com.hanatour.openchat.model.Data

class DetailActivity : AppCompatActivity() {
    lateinit var mData : Data
    lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)


        val data : String =  intent.getStringExtra("data").toString()
        mData = Gson().fromJson(data, Data::class.java)
        Log.d("data", mData.toString())

        binding.atvId.text = mData.id.toString()
        binding.atvUserId.text = mData.userId.toString()
        binding.atvTitle.text = mData.title.toString()
        binding.atvDescription.text = mData.body.toString()

    }


}