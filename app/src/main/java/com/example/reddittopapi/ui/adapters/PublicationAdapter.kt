package com.example.reddittopapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.databinding.FragmentPublicationItemBinding

class PublicationAdapter() :
    androidx.recyclerview.widget.ListAdapter<PublicationTable, PublicationAdapter.ViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentPublicationItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: PublicationTable = currentList[position]
        holder.bind(result)
    }


    class ViewHolder(private val binding: FragmentPublicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(publicationTable: PublicationTable) {
            binding.apply {
                authorAndHoursPassed.text =
                    publicationTable.author + publicationTable.passedTime
                txtTitle.text = publicationTable.title
                txtNumOfComments.text = publicationTable.numberOfComments
                Glide.with(binding.root)
                    .load(publicationTable.thumbnailUrl)
                    .into(imgThumbnail)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PublicationTable>() {

        override fun areItemsTheSame(
            oldItem: PublicationTable,
            newItem: PublicationTable
        ): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }

        override fun areContentsTheSame(
            oldItem: PublicationTable,
            newItem: PublicationTable
        ): Boolean {
            return oldItem == newItem
        }
    }
}