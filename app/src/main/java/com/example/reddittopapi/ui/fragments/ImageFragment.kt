package com.example.reddittopapi.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.reddittopapi.constants.Constants.ID
import com.example.reddittopapi.databinding.FragmentImageBinding
import com.example.reddittopapi.ui.viewModels.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding
    private val imageViewModel by viewModel<ImageViewModel>(parameters = { parametersOf(id) })

    private val id by lazy {
        requireArguments().getString(ID) ?: throw IllegalStateException("No id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewModel.post.observe(viewLifecycleOwner, {
            when (it.isVideo) {
                true -> {
                    binding.urlFullScreen.text = it.fullScreenUrl
                }
                else -> {
                    showImage(it.fullScreenUrl)
                }
            }
        })
        binding.btnSaveImage.setOnClickListener {
            savePicture(binding.imdFullScreen)
        }
    }

    private fun savePicture(iv: ImageView) {
        var fOut: OutputStream? = null
        val folderToSave = context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        try {
            val file = File(
                folderToSave,
                System.currentTimeMillis().toString() + ".jpg"
            )
            fOut = FileOutputStream(file)
            iv.drawable.toBitmap().compress(
                Bitmap.CompressFormat.JPEG,
                85,
                fOut
            )
            fOut.flush()
            fOut.close()
            MediaStore.Images.Media.insertImage(
                context!!.contentResolver,
                file.absolutePath,
                file.name,
                file.name
            )
            Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
    }

    private fun showImage(url: String) {
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progress.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progress.visibility = View.GONE
                    return false
                }
            })
            .into(binding.imdFullScreen)
    }
}
