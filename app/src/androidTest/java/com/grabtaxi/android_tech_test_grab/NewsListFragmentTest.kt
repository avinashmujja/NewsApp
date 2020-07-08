package com.grabtaxi.android_tech_test_grab

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.grabtaxi.android_tech_test_grab.com.grabtaxi.android_tech_test_grab.SingleFragmentActivity
import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.ui.NewsListFragment
import com.grabtaxi.android_tech_test_grab.util.Resource
import com.grabtaxi.android_tech_test_grab.utils.*
import com.grabtaxi.android_tech_test_grab.viewmodel.NewsViewModel
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class NewsListFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)
    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    private val results = MutableLiveData<Resource<List<NewsItems>>>()

    private val newsFragment = TestNewsListFragment()


    @Before
    fun init() {
        val viewModel = Mockito.mock(NewsViewModel::class.java)
        Mockito.`when`(viewModel.results).thenReturn(results)
        newsFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        newsFragment.appExecutors = countingAppExecutors.appExecutors
        activityRule.activity.setFragment(newsFragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }


    @Test
    fun News() {
        Espresso.onView(withId(R.id.progress_bar))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        results.postValue(Resource.loading(null))
        Espresso.onView(withId(R.id.progress_bar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }



    class TestNewsListFragment : NewsListFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }
}