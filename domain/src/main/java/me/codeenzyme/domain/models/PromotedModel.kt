package me.codeenzyme.domain.models


import com.google.gson.annotations.SerializedName

data class PromotedModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("expires_at")
        val expiresAt: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("start_at")
        val startAt: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("subject")
        val subject: Subject,
        @SerializedName("topic")
        val topic: String,
        @SerializedName("tutor")
        val tutor: Tutor
    ) {
        data class Subject(
            @SerializedName("name")
            val name: String
        )

        data class Tutor(
            @SerializedName("firstname")
            val firstname: String,
            @SerializedName("lastname")
            val lastname: String?
        )
    }
}