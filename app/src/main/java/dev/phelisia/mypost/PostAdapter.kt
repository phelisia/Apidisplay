package dev.phelisia.mypost

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.phelisia.mypost.databinding.PostListItemBinding

class PostAdapter(var postlist:List<Post>):RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding=PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost = postlist.get(position)
        with(holder.binding){
            tvUserid.text = currentPost.userId.toString()
            tvTitle.text = currentPost.title
            tvId.text = currentPost.id.toString()
            tvbody.text = currentPost.body
            var context=holder.itemView.context
            holder.binding.cvpost.setOnClickListener {
                val intent=Intent(context,commentActivity::class.java)
                intent.putExtra("POST_ID",currentPost.id)
                context.startActivity(intent)
            }
        }
        }

    override fun getItemCount(): Int {
        return postlist.size
    }
    }


class  PostViewHolder(var binding:PostListItemBinding):
    RecyclerView.ViewHolder(binding.root)

