package com.saputroekosulistiyo.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,  // Menyimpan ID resource untuk nama topik (string)
    val availableCourses: Int,  // Menyimpan jumlah kursus yang tersedia untuk topik ini
    @DrawableRes val imageRes: Int  // Menyimpan ID resource untuk gambar yang terkait dengan topik
)
