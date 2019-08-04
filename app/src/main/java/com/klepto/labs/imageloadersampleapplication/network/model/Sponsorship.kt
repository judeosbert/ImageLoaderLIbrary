package com.klepto.labs.imageloadersampleapplication.network.model


import com.google.gson.annotations.SerializedName
import com.klepto.labs.imageloadersampleapplication.network.model.Sponsor

data class Sponsorship(
    @SerializedName("impressions_id")
    val impressionsId: String?,
    @SerializedName("sponsor")
    val sponsor: Sponsor?,
    @SerializedName("tagline")
    val tagline: String?
)