package com.example.fitme.api

import com.cloudinary.Cloudinary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CloudinaryConfig {
    private const val CLOUD_NAME = "RevMe"
    private const val API_KEY = "927913364514374"
    private const val API_SECRET = "CV3EPFLwJ_3RKyMAOll1P-po1FA"

    val cloudinary: Cloudinary by lazy {
        Cloudinary(mapOf(
            "cloud_name" to CLOUD_NAME,
            "api_key" to API_KEY,
            "api_secret" to API_SECRET
        ))
    }

    suspend fun uploadImage(imagePath: String): String {
        return withContext(Dispatchers.IO) {
            val uploadResult = cloudinary.uploader().upload(imagePath, mapOf("folder" to "fitme"))
            uploadResult["url"].toString()
        }
    }
}
