package com.example.myapp_pedulidigital.data

import android.content.Context
import com.example.myapp_pedulidigital.data.local.MovieLocalDataSource
import com.example.myapp_pedulidigital.data.remote.MovieRemoteDataSource
import com.example.myapp_pedulidigital.model.MovieDetail

class MovieRepository(
    private val context: Context,
    private val remoteSource: MovieRemoteDataSource = MovieRemoteDataSource()
) {
    private val localSource: MovieLocalDataSource = MovieLocalDataSource(context)

    suspend fun fetchNowPlayingMovies() = remoteSource.fetchNowPlayingMovies()

    suspend fun searchMovies(query: String) = remoteSource.searchMovies(query)

    suspend fun fetchUpcomingMovies() = remoteSource.fetchUpcomingMovies()

    suspend fun fetchLatestMovies() = remoteSource.fetchLatestMovies()

    suspend fun fetchPopularMovies() = remoteSource.fetchPopularMovies()

    suspend fun fetchTopRatedMovies() = remoteSource.fetchTopRatedMovies()

    suspend fun getDetailMovie(movieId: Int): Result<MovieDetail> {
        val localData = localSource.getMovieDetailById(movieId)

        return if (localData == null) {
            val remoteData = remoteSource.fetchDetailMovie(movieId)
            localSource.saveMovieDetail(remoteData.getOrDefault(MovieDetail()))
            remoteData
        } else {
            Result.success(localData)
        }
    }
}