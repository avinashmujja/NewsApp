/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grabtaxi.android_tech_test_grab.api

import okhttp3.MediaType
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ApiResponseTest {
    @Test
    fun exception() {
        val exception = Exception("foo")
        val (errorMessage) = ApiResponse.create<String>(exception)
        assertThat<String>(errorMessage, `is`("foo"))
    }

    @Test
    fun success() {
        val apiResponse: ApiSuccessResponse<String> = ApiResponse
            .create<String>(Response.success("foo")) as ApiSuccessResponse<String>
        assertThat<String>(apiResponse.body, `is`("foo"))
    }

    @Test
    fun link() {
        val link = "<https://google.com>"
        val headers = okhttp3.Headers.of("link", link)
        val response = ApiResponse.create<String>(Response.success("success", headers)) as ApiSuccessResponse<String>
        assertThat<String>(response.body , `is`("success"))
    }

    @Test
    fun error() {
        val errorResponse = Response.error<String>(
            400,
            ResponseBody.create(MediaType.parse("application/txt"), "blah")
        )
        val (errorMessage) = ApiResponse.create<String>(errorResponse) as ApiErrorResponse<String>
        assertThat<String>(errorMessage, `is`("blah"))
    }
}