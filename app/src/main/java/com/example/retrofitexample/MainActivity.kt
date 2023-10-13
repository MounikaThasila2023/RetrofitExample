package com.example.retrofitexample

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitexample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://simplifiedcoding.net/demos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiInterface::class.java)

        api.getHeroes().enqueue(object : Callback<List<Question>?> {

            override fun onResponse(
                call: Call<List<Question>?>,
                response: Response<List<Question>?>
            ) {
                val heroList: List<Question> = response.body()!!
                val heroes = arrayOfNulls<String>(heroList.size)

                for (i in heroList.indices) {
                    heroes[i] = heroList[i].createdby
                }
                val listView: ListView = binding.recyclerView
                listView.adapter =
                    ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, heroes)
            }

            override fun onFailure(call: Call<List<Question>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_SHORT).show()
            }

        })

    }
}



