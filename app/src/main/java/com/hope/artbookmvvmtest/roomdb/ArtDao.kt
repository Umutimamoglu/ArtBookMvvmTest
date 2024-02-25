package com.hope.artbookmvvmtest.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ArtDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE) // id cakısırsa ne yapıcak
    suspend fun insertArt(art : Art)// susbend ilemlei arka planda yap

    @Delete
    suspend fun deleteArt(art:Art)

    @Query("SELECt * FROM arts")
    fun observerArts(): LiveData<List<Art>>
}