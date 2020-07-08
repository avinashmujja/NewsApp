
package com.grabtaxi.android_tech_test_grab.util

import androidx.lifecycle.LiveData

/**
 * A LiveData class that has `null` value.
 */
class AbsentLiveData<T : Any?> private constructor(): LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
