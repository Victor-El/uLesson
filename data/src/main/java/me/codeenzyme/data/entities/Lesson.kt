package me.codeenzyme.data.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Lesson(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("expires_at")
    val expiresAt: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("start_at")
    val startAt: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subject")
    @Embedded
    val subject: Subject,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("tutor")
    @Embedded
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
        val lastname: String
    )
}