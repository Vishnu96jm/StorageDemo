package com.jmv.vacay.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Trip(
  val id: String = UUID.randomUUID().toString(),
  val title: String,
  val country: String,
  val details: String
) : Parcelable