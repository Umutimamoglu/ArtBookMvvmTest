package com.hope.artbookmvvmtest.repo

import androidx.lifecycle.LiveData
import com.hope.artbookmvvmtest.api.RetrofitApi
import com.hope.artbookmvvmtest.model.ImageResponse
import com.hope.artbookmvvmtest.roomdb.Art
import com.hope.artbookmvvmtest.roomdb.ArtDao
import com.hope.artbookmvvmtest.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao : ArtDao,
    private  val retrofitApi: RetrofitApi
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)

    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return  artDao.observerArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return  try {
            val response = retrofitApi.imageSearch(imageString)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else{
                Resource.error("Error",null)
            }
        }
        catch (e:Exception) {
            Resource.error("No Data!",null)

        }        }
}