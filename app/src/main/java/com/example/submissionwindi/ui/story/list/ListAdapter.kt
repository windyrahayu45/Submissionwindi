package com.example.submissionwindi.ui.story.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.submissionwindi.data.source.remote.response.ListResponse
import com.example.submissionwindi.data.source.remote.response.Story
import com.example.submissionwindi.databinding.LayoutListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(val binding : LayoutListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Story){
            binding.tvItemName.text = data.name
            binding.imgSource.load(data.photoUrl)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}