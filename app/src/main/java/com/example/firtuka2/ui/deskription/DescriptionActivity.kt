package com.example.firtuka2.ui.deskription

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.firtuka2.R
import com.example.firtuka2.data.repo.DetailData
import com.example.firtuka2.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }

        val detailData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getSerializableExtra(EXTRA_ID, DetailData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_ID) as DetailData
        }

        binding.tvDetailName.text = detailData?.nama
        binding.tvGender.text = detailData?.gender

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))

        Glide.with(binding.root)
            .load(detailData?.image)
            .apply(requestOptions)
            .into(binding.ivDetailPhoto)
    }

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_GENDER = "extra_gender"
        const val EXTRA_IMG = "extra_image"
    }
}