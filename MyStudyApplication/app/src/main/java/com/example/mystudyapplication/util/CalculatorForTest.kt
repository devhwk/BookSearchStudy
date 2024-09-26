package com.example.mystudyapplication.util

class CalculatorForTest {
    fun add(x: Int, y: Int) = x + y
    fun sub(x: Int, y: Int) = x - y
    fun mul(x: Int, y: Int) = x * y

    fun div(x: Int, y: Int) = try {
        x / y
    } catch (e: Exception) {
        null
    }
}