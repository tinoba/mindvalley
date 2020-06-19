package com.tinoba.mindvalleychannels

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tinoba.mindvalleychannels.ui.home.HomeActivity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.tinoba.mindvalleychannels", appContext.packageName)
    }

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun onLaunchCheckPullToRefreshIsDisplayed() {

        ActivityScenario.launch(HomeActivity::class.java)

        onView(withId(R.id.pullToRefreshLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun onNetworkFetchCheckTitle() {

        ActivityScenario.launch(HomeActivity::class.java)
        Thread.sleep(2000)
        onView(withId(R.id.fragmentHomeTitle))
            .check(matches(withText("Channels")))
    }

    @Test
    fun onNetworkFetchCheckNewEpisodesTitle() {

        ActivityScenario.launch(HomeActivity::class.java)
        Thread.sleep(2000)
        onView(withId(R.id.newEpisodesTitle))
            .check(matches(withText("New Episodes")))
    }

    @Test
    fun onNetworkFetchCheckCategoriesTitle() {

        ActivityScenario.launch(HomeActivity::class.java)
        Thread.sleep(2000)
        onView(withId(R.id.categoriesTitle))
            .check(matches(withText("Browse by categories")))
    }

    @Test
    fun onNetworkFetchCheckChannelsExist() {

        ActivityScenario.launch(HomeActivity::class.java)
        Thread.sleep(2000)
        onView(withId(R.id.seriesRecyclerView))
            .check(matches(hasMinimumChildCount(1)))
    }
}