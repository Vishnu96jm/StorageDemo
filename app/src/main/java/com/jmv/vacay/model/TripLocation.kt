
package com.jmv.vacay.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TripLocation(
  val name: String,
  val address: String,
  val locationImageUrl: String?
) : Parcelable