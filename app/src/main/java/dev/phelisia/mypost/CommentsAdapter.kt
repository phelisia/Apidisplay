package dev.phelisia.mypost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.phelisia.mypost.databinding.CommentsListItemBinding


class CommentsAdapter (var commentlist:List<Comment>): RecyclerView.Adapter<CommentsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding= CommentsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment = commentlist.get(position)
        with(holder.binding){
            tvPostIdComment.text=currentComment.postId.toString()
            tvIdComment.text=currentComment.id.toString()
            tvNameComment.text=currentComment.name
            tvCommentsbody.text=currentComment.body
            tvEmailComment.text=currentComment.email


        }

    }

    override fun getItemCount(): Int {
        return commentlist.size
    }

}
class CommentsViewHolder(var binding: CommentsListItemBinding ):RecyclerView.ViewHolder(binding.root){

}