package com.rzaedev.mymovli.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val director: String,
    val starring: String,
    val censorRating: String,
    val genre: String,
    val language: String,
    val subtitle: String,
    val duration: String,
    val posterUrl: String
) : Parcelable
