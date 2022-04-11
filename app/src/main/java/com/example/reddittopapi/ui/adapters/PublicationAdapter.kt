package com.example.reddittopapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittopapi.constants.Constants.POST_PREFETCH_COUNT
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.databinding.FragmentPublicationItemBinding

class PublicationAdapter(
    private val onCLick: (PublicationTable) -> Unit,
    private val onEndReached: () -> Unit
) : androidx.recyclerview.widget.ListAdapter<PublicationTable, PublicationAdapter.ViewHolder>(
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
        holder.bind(result, onCLick)
        if (position == itemCount - POST_PREFETCH_COUNT) {
            onEndReached()
        }
    }

    class ViewHolder(private val binding: FragmentPublicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            publicationTable: PublicationTable,
            onImageClick: (PublicationTable) -> Unit
        ) {
            binding.apply {
                authorAndHoursPassed.text =
                    publicationTable.author + publicationTable.passedTime
                txtTitle.text = publicationTable.title
                txtNumOfComments.text = publicationTable.numberOfComments
                Glide.with(binding.root)
                    .asBitmap()
                    .load(publicationTable.thumbnailUrl)
                    .into(imgThumbnail)
                imgThumbnail.setOnClickListener {
                    onImageClick(publicationTable)
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PublicationTable>() {

        override fun areItemsTheSame(
            oldItem: PublicationTable,
            newItem: PublicationTable
        ): Boolean {
            return oldItem.fullScreenUrl == newItem.fullScreenUrl
        }

        override fun areContentsTheSame(
            oldItem: PublicationTable,
            newItem: PublicationTable
        ): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }
    }
}

