package dev.phelisia.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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
        var retro=ApiClient.buildApiClient((ApiInterface::class.java))
        var request=retro.getComments()
        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if(response.isSuccessful){
                    var commentss=response.body()?: emptyList()
                    displayComments(commentss)

                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

            })
            }
    fun displayComments(commentList:List<Comment>){
        val commentsAdapter=CommentsAdapter(commentList)
        binding.rvcomments.layoutManager=LinearLayoutManager(this)
        binding.rvcomments.adapter=commentsAdapter
    }
        }