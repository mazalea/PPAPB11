package com.example.ppapb11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ppapb11.databinding.ActivityMainBinding
import com.example.ppapb11.model.Users
import com.example.ppapb11.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getAllUsers()
        val userList = ArrayList<String>()

        response.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val thisResult = response.body()
                val data = thisResult?.data?: emptyList()
                if (data.isNotEmpty()){
                    for (i in data){
                        userList.add(i.employeeName)
                    }
                }
                println("this user list ${userList.size}")
                println("this data ${response.body()?.data?.size}")

                val listAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    userList
                )

                binding.lvNama.adapter = listAdapter

            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}