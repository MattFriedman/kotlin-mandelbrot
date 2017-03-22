package com.listoutfitter.precision

import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Created by Matt Friedman <matt.friedman></matt.friedman>@gmail.com> on 3/20/17.
 */
class PreciseTest {

    @Test
    fun compareTo1() {

        val p1 = Precise(0)
        val p2 = Precise(1)

        assertTrue(p1 < p2)
    }

    @Test
    fun compareTo2() {

        val p1 = Precise(1)
        val p2 = Precise(0)

        assertTrue(p1 > p2)
    }

    @Test
    fun compareTo3() {

        val p1 = Precise(1)
        val p2 = Precise(1)

        assertTrue(p1 == p2)
    }

    @Test
    fun compareTo4() {

        val p1 = Precise(1)
        val p2 = Precise(1)

        assertTrue(p1 >= p2)
    }

}
