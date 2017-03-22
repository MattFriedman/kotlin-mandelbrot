package com.listoutfitter.precision

import nl.jqno.equalsverifier.EqualsVerifier
import org.apfloat.Apfloat
import spock.lang.Specification

/**
 * Created by Matt Friedman <matt.friedman@gmail.com> on 3/18/17.
 */
class PreciseSpec extends Specification {

    def "raise to power"() {
        given:
            def p1 = new Precise("2")
        when:
            def p2 = p1.pow(2)
        then:
            p2 == new Precise("4")
    }

    def "examine precision of number over 9"() {
        when:
            def p1 = new Precise("10", 128)
            def p2 = p1 * new Precise("2")
        then:
            println(p1)
            println(p1.delegate.toString())
            println(p2)
            p2 == new Precise("20")
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "verify multiply"() {
        when:
            def p1 = new Precise(x)
            def p2 = new Precise(y)
            def p3 = new Precise(z)
        then:
            p1 * p2 == p3
        where:
            x   | y   | z
            3.0 | 2.0 | 6.0
            "3" | 2F  | 6L
    }


    @SuppressWarnings("GroovyAssignabilityCheck")
    def "verify minus"() {
        when:
            def p1 = new Precise(x)
            def p2 = new Precise(y)
            def p3 = new Precise(z)
        then:
            p1 - p2 == p3
        where:
            x   | y   | z
            3.0 | 2.0 | 1.0
            "3" | 2F  | 1L
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    def "verify Plus and equals"() {
        when:
            def p1 = new Precise(x)
            def p2 = new Precise(y)
            def p3 = new Precise(z)
        then:
            p1 + p2 == p3
        where:
            x     | y     | z
            1.0   | 2.0   | 3.0
            "1.0" | "2.0" | "3.0"
            1L    | 2L    | "3"
            "1"   | 2.0F  | 3L
    }

    def "Verify equals"() {
        when:
            println "verify equals"
            def apf1 = new Apfloat(1.0)
            def apf2 = new Apfloat(2.0)
        then:
            EqualsVerifier.forClass(Precise)
                    .withNonnullFields("delegate")
                    .withPrefabValues(Apfloat, apf1, apf2)
                    .verify()
    }

    def "public Apfloat(float value, long precision, int radix)"() {
        when:
            def p = new Precise(1.0F, 111L, 10)
        then:
            p instanceof Precise
    }

    def "public Apfloat(float value, long precision)"() {
        when:
            def p = new Precise(1.0F, 111L)
        then:
            p instanceof Precise
    }

    def "public Apfloat(float value)"() {
        when:
            def p = new Precise(1.0F)
        then:
            p instanceof Precise
    }

    def "public Apfloat(long value, long precision, int radix)"() {
        when:
            def p = new Precise(1L, 1L, 10)
        then:
            p instanceof Precise
    }

    def "construct with long, long"() {
        when:
            def p = new Precise(1L, 1L)
        then:
            p instanceof Precise
    }

    def "construct with long"() {
        when:
            def p = new Precise(1L)
        then:
            p == new Precise(1L)
    }

    def "construct with string, precision, radix"() {
        when:
            def p = new Precise("1.123", 3L, 10)
        then:
            p instanceof Precise
    }

    def "construct with string and precision"() {
        when:
            def p1 = new Precise("1.111", 3L)
        then:
            p1 instanceof Precise
    }


    def "public Apfloat(double value, long precision)"() {
        when:
            def p = new Precise(1D, 10L)
        then:
            p instanceof Precise
    }

    def "public Apfloat(double value, long precision, int radix)"() {
        when:
            def p = new Precise(1D, 10L, 10)
        then:
            p instanceof Precise
    }
}
