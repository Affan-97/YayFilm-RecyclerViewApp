package com.affan.finalproject


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvData:RecyclerView
    private val listData = ArrayList<Movies>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Daftar Film"
        setContentView(R.layout.activity_main)
        rvData=findViewById(R.id.rv_data)
        rvData.setHasFixedSize(true)
        listData.addAll(getListData())
        showData()
    }

    private fun getListData(): ArrayList<Movies>{
        val dataName = resources.getStringArray(R.array.data_name)

        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataDirector = resources.getStringArray(R.array.data_director)
        val dataDesciption = resources.getStringArray(R.array.data_description)
        val list = ArrayList<Movies>()

        for (i in dataName.indices){
            val heroes = Movies(dataName[i],dataDirector[i],dataDate[i],dataDesciption[i],dataPhoto[i])
            list.add(heroes)
        }
        return list
    }

    private fun showData() {
        rvData.layoutManager = LinearLayoutManager(this)
        val listAdapter = ListAdapter(listData)
        rvData.adapter = listAdapter
        listAdapter.setOnItemClickCallback(object :ListAdapter.OnItemClickCallback{
            override fun onCLicked(data: Movies) {
                displayData(data)
            }



        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if(item.itemId==R.id.about_page){
          val AboutIntent = Intent(this,AboutActivity::class.java)
          startActivity(AboutIntent)
      }
        return super.onOptionsItemSelected(item)
    }
    private fun displayData(data: Movies) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.Extra_data, Detail(data.name,data.director,data.releaseDate,data.desciption,data.photo))
        startActivity(moveWithObjectIntent)

    }
}