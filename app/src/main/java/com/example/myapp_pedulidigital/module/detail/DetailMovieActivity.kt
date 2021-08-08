package com.example.myapp_pedulidigital.module.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.myapp_pedulidigital.data.remote.ApiEndpoint
import com.example.myapp_pedulidigital.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    lateinit var viewModel: DetailViewModel
    lateinit var binding: ActivityDetailMovieBinding

    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_detail_movie)*/

        setupDataBinding()
        processIntent()
        setupViewModel()
        setupObserver()

        viewModel.getDetailMovie(movieId)
    }

    private fun setupDataBinding() {
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupObserver() {
        viewModel.detailMovieResultLiveData.observe(this) {
            viewModel.handleMovieDetailResponse(it)
        }
        viewModel.detailMovieLiveData.observe(this) {
            Glide.with(this).load(ApiEndpoint.BASE_IMAGE_URL + it.backDropPath)
                .into(binding.detailMovieImageView)
            /*binding.movie = it*/
        }
        viewModel.errorMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.setContext(this)
    }

    private fun processIntent() {
        movieId = intent.getIntExtra("movieID", 0)
    }

    fun onLinkClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
