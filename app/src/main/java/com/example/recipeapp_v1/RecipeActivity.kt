package com.example.recipeapp_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipeapp_v1.RetrofitInstance.api
import com.example.recipeapp_v1.databinding.ActivityRecipeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnView.setOnClickListener {
            val intent = Intent(this@RecipeActivity,MainActivity::class.java )
            startActivity(intent)
        }

        binding.btnSave.setOnClickListener {

            postData()
            binding.etAuthor.text.clear()
            binding.etTitle.text.clear()
            binding.etIng.text.clear()
            binding.etInstruction.text.clear()
        }

    }

    private fun postData(){
        val author = binding.etAuthor.text.toString()
        val title = binding.etTitle.text.toString()
        val ingredients = binding.etIng.text.toString()
        val instructions = binding.etInstruction.text.toString()

        api.postData(Data(1, title, author, ingredients, instructions)).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>){
                Log.d("Main-success", "Data Added")
            }
            override fun onFailure(call: Call<Data>, t: Throwable){
                Log.d("Main-Failure", "Data Not Added")
            }
        })

    }
}