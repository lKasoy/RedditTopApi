package com.example.reddittopapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reddittopapi.databinding.FragmentPublicationsListBinding
import com.example.reddittopapi.ui.adapters.PublicationAdapter
import com.example.reddittopapi.ui.viewModels.PublicationsViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PublicationsFragment : Fragment() {

    private lateinit var binding: FragmentPublicationsListBinding
    private val publicationsViewModel by viewModel<PublicationsViewModel>()
    private val publicationAdapter = PublicationAdapter()

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