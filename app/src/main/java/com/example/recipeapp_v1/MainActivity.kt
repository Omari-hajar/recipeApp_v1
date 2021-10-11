package com.example.recipeapp_v1



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp_v1.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var rvMain: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRV()

        getData()

      binding.btnAdd.setOnClickListener {

          val intent = Intent(this@MainActivity,RecipeActivity::class.java )
          startActivity(intent)
      }




    }
    //recycler view setup
    private fun setUpRV(){
        binding.rvMain.apply {
            rvMain = RVAdapter()
            adapter = rvMain
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    //// get data

    private fun getData(){
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getData()
            } catch (e: IOException){
                Log.d("Main-error", e.message.toString())
                return@launchWhenCreated

            }catch (e: HttpException){
                Log.d("Main-error", e.message.toString())
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() !=null){
                rvMain.items = response.body()!!
            }else{
                Log.d("Main-Error", "Response was not successful")
            }
        }
    }





}