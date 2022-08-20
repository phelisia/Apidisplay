package dev.phelisia.mypost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.phelisia.mypost.databinding.CommentsListItemBinding


class CommentsAdapter (var postlist:List<Post>): RecyclerView.Adapter<CommentsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding= CommentsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentPost = postlist.get(position)
        with(holder.binding){
            tvUserid.text = currentPost.userId.toString()
            tvTitle.text = currentPost.title
            tvId.text = currentPost.id.toString()
            tvbody.text = currentPost.body
            var context=holder.itemView.context
            holder.binding.cvpost.setOnClickListener {
                val intent= Intent(context,commentActivity::class.java)
                intent.putExtra("POST_ID",currentPost.id)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return postlist.size
    }

}
class CommentsViewHolder(var binding: CommentsListItemBinding ):RecyclerView.ViewHolder(binding.root){

}