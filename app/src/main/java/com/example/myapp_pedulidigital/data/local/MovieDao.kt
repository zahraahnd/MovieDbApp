package com.example.myapp_pedulidigital.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapp_pedulidigital.model.MovieDetail

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: MovieDetail)

    @Query("SELECT * FROM moviedetail WHERE id = :movieId")
    fun getDetailMovie(movieId: Int): MovieDetail
}