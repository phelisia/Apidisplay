package dev.phelisia.mypost

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.sql.RowId

interface ApiInterface {
    @GET("/posts")
    fun getPosts():Call<List<Post>>
    @GET("/posts/{postId}")
    fun getPostsById(@Path("postId")postId: Int):Call<Post>
    @GET("/comments")
    fun getComments():Call<List<Comment>>
}