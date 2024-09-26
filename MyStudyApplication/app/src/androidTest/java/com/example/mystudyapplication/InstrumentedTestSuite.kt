package com.example.mystudyapplication

import com.example.mystudyapplication.data.db.BookSearchDaoTest
import com.example.mystudyapplication.ui.activity.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@ExperimentalCoroutinesApi
@Suite.SuiteClasses(
    MainActivityTest::class,
    BookSearchDaoTest::class
)
class InstrumentedTestSuite {
}