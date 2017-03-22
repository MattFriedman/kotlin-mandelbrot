package com.listoutfitter.precision

import org.apfloat.Apcomplex
import org.apfloat.ApcomplexMath
import org.apfloat.Apfloat
import org.apfloat.ApfloatMath

/**
 * Created by Matt Friedman <matt.friedman@gmail.com> on 3/18/17.
 */
class Complex {

    internal val delegate: Apcomplex

    companion object Factory {
        fun zero(precision: Long): Complex {
            return Complex(Precise(0, precision), Precise(0, precision))
        }
    }

    fun squared(): Complex {
        return Complex(ApcomplexMath.pow(delegate, 2L))
    }

    fun real(): Precise {
        return Precise(delegate.real())
    }

    fun imag(): Precise {
        return Precise(delegate.imag())
    }

    constructor(real: Precise, imag: Precise) {
        delegate = Apcomplex(real.delegate, imag.delegate)
    }

    constructor(real: String, imag: String) {
        delegate = Apcomplex(Apfloat(real), Apfloat(imag))
    }

    constructor(real: Long, imag: Long) {
        delegate = Apcomplex(Apfloat(real), Apfloat(imag))
    }

    internal constructor(apc: Apcomplex) {
        delegate = Apcomplex(apc.real(), apc.imag())
    }

    operator fun plus(pc: Complex): Complex {
        return Complex(delegate.add(pc.delegate))
    }

    operator fun minus(pc: Complex): Complex {
        return Complex(delegate.subtract(pc.delegate))
    }

    fun multiply(pc: Complex): Complex {
        return times(pc)
    }

    operator fun times(pc: Complex): Complex {
        return Complex(delegate.multiply(pc.delegate))
    }

    operator fun div(pc: Complex): Complex {
        return Complex(delegate.divide(pc.delegate))
    }

    fun abs(): Precise {
        val real = delegate.real()
        val imag = delegate.imag()
        val realPow2 = ApfloatMath.pow(real, 2)
        val imagPow2 = ApfloatMath.pow(imag, 2)
        val sum = realPow2.add(imagPow2)
        val sqrt = ApfloatMath.sqrt(sum)
        return Precise(sqrt)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Complex

        if (delegate != other.delegate) return false

        return true
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }

    override fun toString(): String {
        return delegate.toString(true)
    }

}
