package com.example.personalorganiser.data

import com.google.gson.annotations.SerializedName
import java.util.Map

data class EchoResponse(
    val args: Map<String, String>?,
    val headers: Map<String, String>?,
    val url: String?
)