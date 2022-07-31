package dev.phelisia.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.phelisia.mypost.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }
    fun fetchPosts(){
   var apiclient=ApiClient.buildApiClient(ApiInterface::class.java)
   var request=apiclient.getPosts()
   request.enqueue(object : Callback<List<Post>>{
       override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
           if (response.isSuccessful){
               var posts=response.body()
               Toast.makeText(baseContext,"fetched ${posts!!.size} posts",Toast.LENGTH_LONG).show()
               var adapter=PostAdapter(baseContext,posts)
               Log.d("tag",posts.toString())
               binding.rvadapter.adapter=adapter
               binding.rvadapter.layoutManager=LinearLayoutManager(baseContext)
           }
       }

       override fun onFailure(call: Call<List<Post>>, t: Throwable) {

       }

   })
    }
}