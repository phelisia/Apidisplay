package dev.phelisia.mypost

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.phelisia.mypost.databinding.PostListItemBinding

class PostAdapter(var context: Context, var postlist:List<Post>):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding=PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost=postlist.get(position)
        with(holder.binding){
            tvUserid.text=currentPost.userId.toString()
            tvId.text=currentPost.id.toString()
            tvTitle.text=currentPost.title.toString()
            tvbody.text=currentPost.body.toString()

        }
    }

    override fun getItemCount(): Int {
        return postlist.size
    }
}


class  PostViewHolder(var binding:PostListItemBinding):
    RecyclerView.ViewHolder(binding.root)

