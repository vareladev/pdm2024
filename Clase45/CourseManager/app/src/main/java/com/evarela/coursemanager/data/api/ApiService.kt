package com.evarela.coursemanager.data.api

import com.evarela.coursemanager.utils.Constants
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Post course
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_COURSE_PATH)
    suspend fun insertCourse(@Body course : CourseApi) : ApiResponseSuccessful

    // Select all courses
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.GET_ALL_COURSES_PATH)
    suspend fun getAllCourses() : List<CourseApi>

    // Select course
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.GET_COURSE_PATH)
    suspend fun getCourse(@Query("id") id : String) : CourseApi

    // delete course
    @Headers(value = ["Content-Type: application/json"])
    @DELETE(value = Constants.API_PATH + Constants.DELETE_COURSE_PATH)
    suspend fun deleteCourse(@Query("id") id : String): ApiResponseSuccessful

    //  update course
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = Constants.API_PATH + Constants.PATCH_COURSE_PATH)
    suspend fun updateCourse( @Query("id") id : String, @Body course : CourseApi) : ApiResponseSuccessful
}