package com.example.reddittopapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.reddittopapi.R
import com.example.reddittopapi.constants.Constants.ID
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.databinding.FragmentPublicationsListBinding
import com.example.reddittopapi.ui.adapters.PublicationAdapter
import com.example.reddittopapi.ui.viewModels.PublicationsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PublicationsFragment : Fragment() {

    private lateinit var binding: FragmentPublicationsListBinding
    private val publicationsViewModel by viewModel<PublicationsViewModel>()
    private val publicationAdapter = PublicationAdapter(
        onCLick = { publication: PublicationTable ->
            val publicationBundle = bundleOf(
                ID to publication.thumbnailUrl
            )
            parentFragmentManager.commit {
                replace<ImageFragment>(R.id.container, "imageFragment", args = publicationBundle)
                setReorderingAllowed(true)
                addToBackStack("publicationsFragment")
            }
        },
        onEndReached = { publicationsViewModel.fetchData() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicationsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.adapter = publicationAdapter

        publicationsViewModel.kind.observe(viewLifecycleOwner, {
            publicationAdapter.submitList(it)
        })
    }
}