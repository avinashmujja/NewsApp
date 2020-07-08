
package com.grabtaxi.android_tech_test_grab.util

import com.grabtaxi.android_tech_test_grab.AppExecutors

import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}
