package com.example.fitme.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fitme.R
import com.example.fitme.api.CloudinaryConfig
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    lateinit var circleImage_avatar : CircleImageView
    private val pickImageRequestCode = 1000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initView(view)
        circleImage_avatar.setOnClickListener {
            openGalleryForImage()
        }

        return  view
    }

    fun initView(view: View) {
        circleImage_avatar = view.findViewById(R.id.circleImage_avatar)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, pickImageRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageRequestCode && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(selectedImageUri!!, filePathColumn, null, null, null)
            cursor?.moveToFirst()
            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath = columnIndex?.let { cursor.getString(it) }
            cursor?.close()

            if (picturePath != null) {
                uploadImageToCloudinary(picturePath)
            }
        }
    }
    private fun uploadImageToCloudinary(imagePath: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = CloudinaryConfig.uploadImage(imagePath)
                withContext(Dispatchers.Main) {
                    if (imageUrl.isNotEmpty()) {
                        Picasso.get().load(imageUrl).into(circleImage_avatar)
                        Snackbar.make(circleImage_avatar, "Image uploaded successfully!", Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(circleImage_avatar, "Image upload failed!", Snackbar.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Snackbar.make(circleImage_avatar, "Image upload failed: ${e.message}", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}