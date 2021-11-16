package com.example.schooltest

import kotlin.math.pow
import kotlin.random.Random

class Operator {
    var a = arrayOfNulls<Int>(5)
    var b = arrayOfNulls<Int>(5)
    var points:Array<Double> = arrayOf(1.0, 1.0, 1.0, 1.0, 1.0)
    var operation:Array<String> = arrayOf("+", "-", "*", "/", "&")

    init {
        for (i in 0..4) {
            a[i] = Random.nextInt(-99, 99)
            b[i] = Random.nextInt(-99,99)
            operation.shuffle()
        }
    }
    fun result(a:Int?, b: Int?, str:String): Int {
        if (a!=null && b!=null){
            when (str) {
                "+" -> return a + b
                "-" -> return a - b
                "*" -> return a * b
                "/" -> return a / b
                "&" -> return a and b
            }
        }
        return 0
    }
}