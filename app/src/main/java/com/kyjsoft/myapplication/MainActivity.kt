package com.kyjsoft.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.kyjsoft.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentTime = Calendar.getInstance().time
        Log.i("TAG-currentTime", currentTime.toString())

        binding.recycler.adapter = MyAdapter(this, items)

        val tour: String = assets.open("tour.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        var tourItem = gson.fromJson(tour, Array<Item>::class.java)

        tourItem.let {
            it.sortedByDescending { it.time }.forEach {
                items.add(Item(it.imgUrl, it.tourName, it.time))
                Log.i("Tag-image", it.imgUrl)
            }
        }

    }





}