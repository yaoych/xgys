package xgys.math

import kotlin.math.cos
import kotlin.math.sin

open class Matrix(val rows: Int, val cols: Int) {
    val data: FloatArray
    init {
        assert(rows > 0)
        assert(cols > 0)
        data = FloatArray(rows * cols)
    }
}

class Matrix4: Cloneable {

    val data = FloatArray(16)

    init {
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                this[i, j] = if (i == j) 1.0f else 0.0f
            }
        }
    }

    override fun clone(): Matrix4 {
        var ret = Matrix4()
        for (i in 0..16) ret.data[i] = data[i]
        return ret
    }

    inline operator fun set(row: Int, col: Int, value: Float) {
        data[row * 4 + col] = value
    }

    inline operator fun get(row: Int, col: Int) = data[row * 4 + col]

    inline operator fun plus(that: Matrix4) :Matrix4 {
        val sum = Matrix4()
        for (i in 0 .. 4) {
            for (j in 0 .. 4) {
                sum[i, j] = this[i, j] + that[i, j]
            }
        }
        return sum
    }

    inline operator fun minus(that: Matrix4) :Matrix4 {
        val sum = Matrix4()
        for (i in 0 .. 4) {
            for (j in 0 .. 4) {
                sum[i, j] = this[i, j] - that[i, j]
            }
        }
        return sum
    }

    inline operator fun times(that: Matrix4) :Matrix4 {
        val sum = Matrix4()
        for (i in 0..4) {
            for (j in 0..4) {
                var a = 0.0f
                for (k in 0..4) {
                    a += this[i, k] * that[k, j]
                }
                sum[i, j] = a
            }
        }
        return sum
    }

    inline operator fun times(that: Vector4) :Vector4 {
        val ret = Vector4()
        for (i in 0..4) {
            var a = 0.0f
            for (j in 0..4) {
                a += this[i, j] * that[j]
            }
            ret[i] = a
        }
        return ret
    }

    inline operator fun times(that: Float) :Matrix4 {
        val ret = Matrix4()
        for (i in 0..4) {
            for (j in 0..4) {
                ret[i, j] = this[i, j] * that
            }
        }
        return ret
    }

    companion object {
        inline fun identity(): Matrix4 {
            val mat = Matrix4()
            for (i in 0..4) {
                for (j in 0..4) {
                    mat[i, j] = if (i == j) 1.0f else 0.0f
                }
            }
            return mat
        }

        inline fun translate(vector: Vector3): Matrix4 {
            val mat = identity()
            mat[0, 3] = vector.x
            mat[1, 3] = vector.y
            mat[2, 3] = vector.z
            return mat
        }

        inline fun scale(vector: Vector3) : Matrix4 {
            val mat = identity()
            mat[0, 0] = vector.x
            mat[1, 1] = vector.y
            mat[2, 2] = vector.z
            return mat
        }

        inline fun rotateByX(angle: Float) :Matrix4 {
            val mat = identity()
            val cosa = cos(angle)
            val sina = sin(angle)
            mat[0, 0] = cosa
            mat[1, 1] = cosa
            mat[0, 1] = -sina
            mat[1, 0] = sina
            return mat
        }

        inline fun rotateByY(angle: Float) :Matrix4 {
            val mat = identity()
            val cosa = cos(angle)
            val sina = sin(angle)
            mat[1, 1] = cosa
            mat[2, 2] = cosa
            mat[1, 2] = -sina
            mat[2, 1] = sina
            return mat
        }

        inline fun rotateByZ(angle: Float) :Matrix4 {
            val mat = identity()
            val cosa = cos(angle)
            val sina = sin(angle)
            mat[0, 0] = cosa
            mat[2, 2] = cosa
            mat[0, 2] = sina
            mat[2, 0] = -sina
            return mat
        }

        inline fun symmetry(x:Boolean, y:Boolean, z:Boolean) :Matrix4 {
            val mat = identity()
            if (x) mat[0, 0] = -1.0f
            if (y) mat[1, 1] = -1.0f
            if (z) mat[2, 2] = -1.0f
            return mat
        }
    }
}