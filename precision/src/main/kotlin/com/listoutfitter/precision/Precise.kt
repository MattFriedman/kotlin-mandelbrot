package com.listoutfitter.precision

import org.apfloat.Apfloat
import org.apfloat.ApfloatMath

class Precise {

    internal val delegate: Apfloat

    fun pow(exponent: Long): Precise {
        return Precise(ApfloatMath.pow(delegate, exponent))
    }

    operator fun compareTo(other: Precise): Int {
        return delegate.compareTo(other.delegate)
    }

    constructor(string: String) {
        delegate = Apfloat(string)
    }

    constructor(string: String, precision: Long, radix: Int) {
        delegate = Apfloat(string, precision, radix)
    }

    constructor(string: String, precision: Long) {
        delegate = Apfloat(string, precision)
    }

    constructor(float: Float) {
        delegate = Apfloat(float)
    }

    constructor(float: Float, precision: Long) {
        delegate = Apfloat(float, precision)
    }

    constructor(float: Float, precision: Long, radix: Int) {
        delegate = Apfloat(float, precision, radix)
    }

    constructor(long: Long) {
        delegate = Apfloat(long)
    }

    constructor(long: Long, precision: Long) {
        delegate = Apfloat(long, precision)
    }

    constructor(long: Long, precision: Long, radix: Int) {
        delegate = Apfloat(long, precision, radix)
    }

    constructor(double: Double) {
        delegate = Apfloat(double)
    }

    constructor(double: Double, precision: Long) {
        delegate = Apfloat(double, precision)
    }

    constructor(double: Double, precision: Long, radix: Int) {
        delegate = Apfloat(double, precision, radix)
    }

    operator fun plus(precise: Precise): Precise {
        return Precise(delegate.add(precise.delegate))
    }

    operator fun minus(precise: Precise): Precise {
        return Precise(delegate.subtract(precise.delegate))
    }

    /**
     * Used by groovy
     */
    fun multiply(precise: Precise): Precise {
        return times(precise)
    }

    operator fun times(precise: Precise): Precise {
        return Precise(delegate.multiply(precise.delegate))
    }

    operator fun div(precise: Precise): Precise {
        return Precise(delegate.divide(precise.delegate))
    }

    override fun toString(): String {
        return delegate.toString()
    }

    internal constructor(apf: Apfloat) {
        delegate = apf
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Precise

        if (delegate != other.delegate) return false

        return true
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }

}
