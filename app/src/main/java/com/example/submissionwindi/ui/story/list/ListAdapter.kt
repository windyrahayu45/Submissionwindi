package com.example.submissionwindi.ui.story.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.submissionwindi.data.source.remote.response.ListResponse
import com.example.submissionwindi.data.source.remote.response.Story
import com.example.submissionwindi.databinding.LayoutListBinding

class ListAdapter(
    private val list: List<Story>,
    private val onClick: (Story) -> Unit
    ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(private val binding : LayoutListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Story,
                 onClick: (Story) -> Unit){
            binding.tvItemName.text = data.name
            binding.imgSource.load(data.photoUrl)

            binding.root.setOnClickListener {
                onClick.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(
            data = item, onClick
        )
    }

    override fun getItemCount() = list.size
}