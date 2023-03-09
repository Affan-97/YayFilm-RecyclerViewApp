package com.affan.finalproject

import android.content.Intent
import android.os.Build

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var tvTitle:TextView
    private lateinit var tvdate:TextView
    private lateinit var tvDirector:TextView
    private lateinit var tvDesc:TextView
    private lateinit var imageView: ImageView
    private lateinit var shareBtn:Button

    companion object {
        const val Extra_data = "DATA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail Film"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

              tvTitle = findViewById(R.id.tvTitle)
              tvDirector = findViewById(R.id.tvDirector)
              tvdate = findViewById(R.id.tvDate)
        tvDesc = findViewById(R.id.tvDesc)
        imageView = findViewById(R.id.imageView)
        shareBtn = findViewById(R.id.action_share)
              val data = if (Build.VERSION.SDK_INT >= 33) {
                  intent.getParcelableExtra(Extra_data, Detail::class.java)
              } else {
                  @Suppress("DEPRECATION")
                  intent.getParcelableExtra(Extra_data)
              }
              if (data != null) {
                    Glide.with(baseContext).load(data.photo).into(imageView)
                  tvTitle.text = data.name.toString()
                  tvDirector.text = data.director.toString()
                  tvdate.text = data.releaseDate.toString()
                  tvDesc.text = data.desciption.toString()
              }
        shareBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id==R.id.action_share){
            val shareIntent:Intent = Intent(Intent.ACTION_SEND)

            shareIntent.setType("text/plain");

            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.about_page->{      val AboutIntent = Intent(this,AboutActivity::class.java)
                startActivity(AboutIntent)}
        }

        return super.onOptionsItemSelected(item)
    }
}