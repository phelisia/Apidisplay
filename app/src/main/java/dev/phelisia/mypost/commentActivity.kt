package dev.phelisia.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.phelisia.mypost.databinding.ActivityCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class commentActivity : AppCompatActivity() {
    var postId=0
    lateinit var binding:ActivityCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtainpost()
        fetchpostbyId()
        setuptoolbar()
        fetchpostbyIdcomments()

    }
    fun obtainpost(){
        postId=intent.extras?.getInt("POST_ID",)?:0
    }
    fun fetchpostbyId(){
        val apiclient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiclient.getPostsById(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful){
                    var post=response.body()
                    binding.tvPostTitle.text=post?.title
                    binding.tvPostBody.text=post?.body
                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }
    fun setuptoolbar(){
        setSupportActionBar(binding.tbcomments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    fun fetchpostbyIdcomments(){

    }
}