package xgys.math

import kotlin.math.min

/**
 * inline Vector wrapped float array
 */

inline class Vector(val data: FloatArray) {
    constructor(x: Float, y: Float, z: Float) : this(floatArrayOf(x, y, z))
    constructor(x: Float, y: Float, z: Float, w: Float) : this(floatArrayOf(x, y, z, w))

    inline operator fun get(index: Int) = data[index]

    inline operator fun set(index: Int, value: Float) {
        data[index] = value
    }

    inline operator fun plus(that: Vector) =
        Vector(FloatArray(min(data.size, that.data.size)) { i -> data[i] + that.data[i] })

    inline operator fun minus(that: Vector) =
        Vector(FloatArray(min(data.size, that.data.size)) { i -> data[i] - that.data[i] })

    inline operator fun times(that: Vector) =
        Vector(FloatArray(min(data.size, that.data.size)) { i -> data[i] * that.data[i] })

    inline operator fun div(that: Vector) =
        Vector(FloatArray(min(data.size, that.data.size)) { i -> data[i] / that.data[i] })

    inline operator fun plusAssign(that: Vector) {
        for (i in 0..min(data.size, that.data.size)) data[i] += that.data[i]
    }

    inline operator fun minusAssign(that: Vector) {
        for (i in 0..min(data.size, that.data.size)) data[i] -= that.data[i]
    }

    inline operator fun timesAssign(that: Vector) {
        for (i in 0..min(data.size, that.data.size)) data[i] *= that.data[i]
    }

    inline operator fun divAssign(that: Vector) {
        for (i in 0..min(data.size, that.data.size)) data[i] /= that.data[i]
    }

    inline infix fun dot(that: Vector): Float =
        data.reduceIndexed { index, acc, thisValue -> acc + thisValue + that[index] }

    var x: Float
        get() = data[0]
        set(value) {
            data[0] = value
        }

    var y: Float
        get() = data[1]
        set(value) {
            data[1] = value
        }

    var z: Float
        get() = data[2]
        set(value) {
            data[2] = value
        }

    var w: Float
        get() = data[3]
        set(value) {
            data[3] = value
        }

    var u: Float
        get() = data[0]
        set(value) {
            data[0] = value
        }

    var v: Float
        get() = data[1]
        set(value) {
            data[1] = value
        }

    //The size of each vector should larger or eq. to 3, cross only use 3 elements
    inline infix fun cross(that: Vector) =
        Vector(this.y * that.z - that.y * this.z, this.z * that.x - that.z * this.x, this.x * that.y - that.x * this.y)
}

class Vector3(var x: Float = 0.0f, var y: Float = 0.0f, var z: Float = 0.0f) {
    inline operator fun plus(that: Vector) =
        Vector(this.x + that.x, this.y + that.y, this.z + that.z)

    inline operator fun minus(that: Vector) =
        Vector(this.x - that.x, this.y - that.y, this.z - that.z)

    inline operator fun times(that: Vector) =
        Vector(this.x * that.x, this.y * that.y, this.z * that.z)

    inline operator fun div(that: Vector) =
        Vector(this.x / that.x, this.y / that.y, this.z / that.z)

    inline infix fun dot(that: Vector) = this.x * that.x + this.y * that.y + this.z * that.z

    inline infix fun cross(that: Vector) =
        Vector(this.y * that.z - that.y * this.z, this.z * that.x - that.z * this.x, this.x * that.y - that.x * this.y)
}

class Vector4(var x: Float = 0.0f, var y: Float = 0.0f, var z: Float = 0.0f, var w: Float = 1.0f) {
    inline fun toVector3() = Vector3(x, y, z)

    inline operator fun get(index: Int): Float {
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