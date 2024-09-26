package com.example.mystudyapplication.util

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class CalculatorForTestTest {
//    private val cal = CalculatorForTest()
    private lateinit var cal: CalculatorForTest

    @Before
    fun setUp() {
        cal = CalculatorForTest()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `add test`() {
        val x = 4
        val y = 2

        val result = cal.add(x, y)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `sub test`() {
        val x = 4
        val y = 2

        val result = cal.sub(x, y)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `mul test`() {
        val x = 4
        val y = 2

        val result = cal.mul(x, y)

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `div test`() {
        val x = 4
        val y = 0

        val result = cal.div(x, y)

        assertThat(result).isEqualTo(null)
    }
}