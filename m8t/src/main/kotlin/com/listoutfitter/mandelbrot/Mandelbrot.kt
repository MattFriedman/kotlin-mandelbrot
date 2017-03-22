package com.listoutfitter.mandelbrot

import com.listoutfitter.precision.Complex
import com.listoutfitter.precision.Precise
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.io.File
import javax.imageio.ImageIO

/**
 * Created by Matt Friedman <matt.friedman@gmail.com> on 3/18/17.
 */
fun main(args: Array<String>) {

    val precision = 8L

    val realStart = Precise(-2.0, precision)
    val realEnd = Precise(1.0, precision)
    val imagStart = Precise(-1.5, precision)
    val imagEnd = Precise(1.5, precision)

    val rows: Int = 64
    val cols: Int = rows

    val rowsPrecise = Precise(rows.toLong(), precision)
    val colsPrecise = Precise(cols.toLong(), precision)

    val realDistance = realEnd - realStart
    val imagDistance = imagEnd - imagStart

    val incrementReal = realDistance / colsPrecise
    val incrementImag = imagDistance / rowsPrecise

    fun pointToComplex(point: Point): Complex {

        val px = point.x.toString()
        val py = point.y.toString()

        val preciseX = Precise(px, precision)
        val preciseY = Precise(py, precision)

        val x = realStart + incrementReal * preciseX
        val y = imagStart + incrementImag * preciseY

        return Complex(x, y)
    }

    val zero = Complex.zero(precision)
    val four = Precise(4, precision)
    val maxIterations = 1000

    // http://beej.us/blog/data/mandelbrot-set/
    fun computeM8t(params: Params): Result {

        val c = params.complex

        var z = zero

        var numIterations = 0

        var inSet = false

        while (z.abs() <= four) {

            z = z.squared() + c

            if (++numIterations >= maxIterations) {
                inSet = true
                break
            }
        }

        return Result(params, inSet, numIterations)
    }

    val image = BufferedImage(rows + 1, cols + 1, TYPE_INT_RGB)

    (0..rows).map { row -> (0..cols).map { col -> Point(row, col) } }
            .flatMap { it }
            .map { Params(it, pointToComplex(it)) }
            .map(::computeM8t)
            .forEach { (params, inSet) ->
                val color = if (inSet) 0 else 255
                image.setRGB(params.point.x, params.point.y, color)
            }

    ImageIO.write(image, "png", File("/tmp/mandelbrot.png"))

} // end main

data class Point(val x: Int, val y: Int)
data class Params(val point: Point, val complex: Complex)
data class Result(val params: Params, val inSet: Boolean, val numIter: Int)
