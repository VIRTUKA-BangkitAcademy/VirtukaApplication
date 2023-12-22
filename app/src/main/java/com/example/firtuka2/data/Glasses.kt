package com.example.firtuka2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Glasses(
    val name: String,
    val gender: String,
    val photo: Int
) : Parcelable
