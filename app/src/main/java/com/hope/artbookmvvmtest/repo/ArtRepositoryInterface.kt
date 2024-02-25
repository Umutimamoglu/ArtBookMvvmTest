package com.hope.artbookmvvmtest.repo

import androidx.lifecycle.LiveData
import com.hope.artbookmvvmtest.model.ImageResponse
import com.hope.artbookmvvmtest.roomdb.Art
import com.hope.artbookmvvmtest.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(srt: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>

}