package com.example.reddittopapi.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.reddittopapi.databinding.FragmentLentaBinding
import com.example.reddittopapi.ui.viewModels.LentaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Lenta : Fragment() {

    private lateinit var binding: FragmentLentaBinding
    private val lentaViewModel by viewModel<LentaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLentaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("test","dsadsadsa")
        Toast.makeText(context, "dsadasd", Toast.LENGTH_SHORT).show()

        lentaViewModel.fetchData()

        lentaViewModel.kind.observe(viewLifecycleOwner, {
            binding.txtKind.text = it.kind
            Log.d("test", it.kind)
        })
    }
}