package xgys.math

import org.junit.Test
import kotlin.math.abs

class VectorTest {

    @Test
    fun testDoc() {
        val a = Vector3(1.0f, 2.0f, 3.0f)
        val b = Vector3(2.0f, 1.0f, 0.0f)
        val c = a + b
        assert(abs(c.x - 3.0f) < 0.001f)
        assert(abs(c.y - 3.0f) < 0.001f)
        assert(abs(c.z - 3.0f) < 0.001f)
    }
}