
package com.grabtaxi.android_tech_test_grab.utils
import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.grabtaxi.android_tech_test_grab.TestApp

/**
 * Custom runner to disable dependency injection.
 */
class GithubTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}
