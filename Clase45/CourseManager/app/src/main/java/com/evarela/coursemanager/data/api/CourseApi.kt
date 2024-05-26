package com.evarela.coursemanager.data.api


import com.evarela.coursemanager.utils.Constants
import com.google.gson.annotations.SerializedName

data class CourseApi(
    @SerializedName(value = Constants.COURSE_ID)
    val id : String? = null,

    @SerializedName(value = Constants.COURSE_CODE)
    val code : String = "",

    @SerializedName(value = Constants.COURSE_TITLE)
    val title : String = "",

    @SerializedName(value = Constants.COURSE_DESCRIPTION)
    val description : String = "",

    @SerializedName(value = Constants.COURSE_CATEGORY)
    val category : String = "",

    @SerializedName(value = Constants.COURSE_TOPIC_LIST)
    val topicList : MutableList<TopicApi> = arrayListOf()
)

data class TopicApi (
    @SerializedName(value = Constants.TOPIC_TITLE)
    val title : String = "",

)