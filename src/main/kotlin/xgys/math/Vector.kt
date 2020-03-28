package xgys.math

import kotlin.math.min

/**
 * inline Vector wrapped float array
 */

class Vector3(var x: Float = 0.0f, var y: Float = 0.0f, var z: Float = 0.0f) {
    operator fun plus(that: Vector3) =
        Vector3(this.x + that.x, this.y + that.y, this.z + that.z)

    operator fun minus(that: Vector3) =
        Vector3(this.x - that.x, this.y - that.y, this.z - that.z)

    operator fun times(that: Vector3) =
        Vector3(this.x * that.x, this.y * that.y, this.z * that.z)

    operator fun div(that: Vector3) =
        Vector3(this.x / that.x, this.y / that.y, this.z / that.z)

    infix fun dot(that: Vector3) = this.x * that.x + this.y * that.y + this.z * that.z

    infix fun cross(that: Vector3) =
        Vector3(
            this.y * that.z - that.y * this.z,
            this.z * that.x - that.z * this.x,
            this.x * that.y - that.x * this.y
        )
}

class Vector4(var x: Float = 0.0f, var y: Float = 0.0f, var z: Float = 0.0f, var w: Float = 1.0f) {

    fun toVector3() = Vector3(x, y, z)

    operator fun get(index: Int): Float {
        assert(index in 0..4)
        return when (index) {
            0 -> x
            1 -> y
            2 -> z
            4 -> w
            else -> 0.0f
        }
    }

    operator fun set(index: Int, value: Float) {
        assert(index in 0..4)
        when (index) {
            0 -> x = value
            1 -> y = value
            2 -> z = value
            3 -> w = value
        }
    }
}