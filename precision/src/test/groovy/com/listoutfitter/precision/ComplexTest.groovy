package com.listoutfitter.precision

import nl.jqno.equalsverifier.EqualsVerifier
import org.apfloat.Apfloat
import spock.lang.Specification

/**
 * Created by Matt Friedman <matt.friedman@gmail.com> on 3/18/17.
 */
class ComplexTest extends Specification {


    def "test square" () {
        given:
            def c = new Complex("2", "4")
        when:
            def result = c.squared()
        then:
            result == new Complex("-10", "10")
    }

    def "test divide"() {
        given:
            def real = new Precise(r1)
            def imag = new Precise(i1)
            def complex1 = new Complex(real, imag)

            def real2 = new Precise(r2)
            def imag2 = new Precise(i2)
            def complex2 = new Complex(real2, imag2)

            def real3 = new Precise(r3)
            def imag3 = new Precise(i3)
            def expected = new Complex(real3, imag3)
        when:
            def actual = complex1 / complex2
        then:
            actual == expected
        where:
            r1 | i1 | r2 | i2 | r3   | i3
            10 | 10 | 10 | 10 | 1    | 0
            2  | 2  | 1  | 1  | 2    | 0
            2  | 2  | 4  | 4  | 0.5F | 0
    }

    def "test times"() {
        given:
            def real = new Precise("3.000")
            def imag = new Precise("4.000")
            def complex1 = new Complex(real, imag)

            def real2 = new Precise(2)
            def imag2 = new Precise(3)
            def complex2 = new Complex(real2, imag2)

            def real3 = new Precise(-6)
            def imag3 = new Precise(17)
            def expected = new Complex(real3, imag3)
        when:
            //  (3 + 4i) * (2 + 3i)
            //   = 6 + 9i + 8i + 12i^2
            //   = 6 + 17i -12
            //   = -6 + 17i
            def actual = complex1 * complex2
        then:
            actual == expected
    }

    def "test minus"() {
        given:
            def real = new Precise("3.000")
            def imag = new Precise("4.000")
            def complex1 = new Complex(real, imag)

            def real2 = new Precise(1)
            def imag2 = new Precise(2)
            def complex2 = new Complex(real2, imag2)

            def real3 = new Precise(2)
            def imag3 = new Precise(2)
            def expected = new Complex(real3, imag3)
        when:
            def actual = complex1 - complex2
        then:
            actual == expected
    }

    def "test plus"() {
        given:
            def real = new Precise("3.000")
            def imag = new Precise("4.000")
            def complex1 = new Complex(real, imag)

            def real2 = new Precise(1)
            def imag2 = new Precise(2)
            def complex2 = new Complex(real2, imag2)

            def real3 = new Precise(4)
            def imag3 = new Precise(6)
            def expected = new Complex(real3, imag3)
        when:
            def actual = complex1 + complex2
        then:
            actual == expected
    }

    def "test absolute value"() {
        given:
            // reminder to start with an appropriate precision, or you may get incorrect results.
            // but, infinite precision will not work as abs() uses the sqrt function.
            def real = new Precise("3.000")
            def imag = new Precise("4.000")
            def complex = new Complex(real, imag)
        when:
            def absoluteValue = complex.abs()
        then:
            absoluteValue == new Precise(5)
    }

    def "verify equals"() {
        when:
            def apf1 = new Apfloat(1D)
            def apf2 = new Apfloat(2D)
        then:
            EqualsVerifier.forClass(Complex)
                    .withPrefabValues(Apfloat, apf1, apf2)
                    .verify()
    }
}
