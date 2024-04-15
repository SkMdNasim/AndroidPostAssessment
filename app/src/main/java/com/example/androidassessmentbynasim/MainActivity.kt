package com.example.androidassessmentbynasim

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassessmentbynasim.adapter.RecyclerViewListAdapter
import com.example.androidassessmentbynasim.databinding.ActivityMainBinding
import com.example.androidassessmentbynasim.viewModel.MainActivityViewModel
import com.google.gson.Gson
import com.hanatour.openchat.model.Data


class MainActivity : AppCompatActivity() {
    lateinit var mainActivityViewModel : MainActivityViewModel
    var pageCount = 0
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: RecyclerViewListAdapter
    var alData : ArrayList<Data> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.llPB.visibility =  View.GONE
        binding.rvPostList.setLayoutManager(LinearLayoutManager(this))
        adapter = RecyclerViewListAdapter(this, alData)
        adapter.setClickListener(object : RecyclerViewListAdapter.ItemClickListener {
            override fun onItemClick(data: Data) {
                Log.d("data" , data.toString())
                val intentData: String = Gson().toJson(data)
                val intent = Intent(this@MainActivity, DetailActivity::class.java);
                intent.putExtra("data", intentData)
                startActivity(intent);
            }
        })
        binding.rvPostList.setAdapter(adapter)

        binding.mainPB.visibility = View.VISIBLE
        mainActivityViewModel.getListData(this,pageCount)

        val scrollChangeListener: NestedScrollView.OnScrollChangeListener =
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                        pageCount++
                        mainActivityViewModel.getListData(this,pageCount)
                        binding.llPB.visibility = View.VISIBLE
                }
            }

        binding.nestedSV.setOnScrollChangeListener(scrollChangeListener)

        mainActivityViewModel.apiResponse.observe(this){

            try {
                if(it?.body()!= null){
                    Log.d("responce" , it.body().toString())
                    val data = it.body()
                    val arrayList = ArrayList(data)
                    alData.addAll(arrayList)
                    adapter.notifyDataSetChanged()
                    binding.mainPB.visibility = View.GONE
                    binding.llPB.visibility = View.GONE
                }


            }catch (e :Exception){
                e.printStackTrace()
            }finally {
               // binding.progressBar.visibility = View.GONE
            }

        }
    }
}