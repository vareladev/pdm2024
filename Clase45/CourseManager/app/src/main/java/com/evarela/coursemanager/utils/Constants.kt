package com.evarela.coursemanager.utils

object Constants {
    // api service
    const val BASE_URL = "http://10.149.15.206:3000"
    const val API_PATH = "/api"

    // Insert course
    const val POST_COURSE_PATH = "/postcourse"
    const val COURSE_ID = "_id"
    const val COURSE_CODE = "code"
    const val COURSE_TITLE = "title"
    const val COURSE_DESCRIPTION = "description"
    const val COURSE_CATEGORY = "category"
    const val COURSE_TOPIC_LIST = "topics"
    const val TOPIC_ID = "_id"
    const val TOPIC_TITLE = "title"

    // Select all courses
    const val GET_ALL_COURSES_PATH = "/getallcourses"

    // Select course
    const val GET_COURSE_PATH = "/getcourse"

    // Update course
    const val PATCH_COURSE_PATH = "/updatecourse"

    // Delete course
    const val DELETE_COURSE_PATH = "/deletecourse"

    // Api response
    const val RESPONSE_SUCCESSFUL = "result"
    const val RESPONSE_ERROR = "message"
}