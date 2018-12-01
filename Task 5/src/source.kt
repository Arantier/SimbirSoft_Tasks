import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.truncate

fun checkForCross(rect1: Array<Int>, rect2: Array<Int>): Boolean {
    return (((rect1[0] in (rect2[0] + 1) until rect2[2]) || (rect1[2] in (rect2[0] + 1) until rect2[2]))
            && ((rect1[1] in (rect2[1] + 1) until rect2[3]) || (rect1[3] in (rect2[1] + 1) until rect2[3])))
}


fun findSumOfSquares(num: Int): Int {
    fun Int.checkForSquare(): Boolean {
        return truncate(sqrt(this.toDouble())).pow(2).toInt() == this
    }


    fun Int.splitOnAddents(num: Int): Array<Array<Int>> {
        lateinit var addents: Array<Int>
        val superAddents = ArrayList<Array<Int>>()
        if (num < 0 || num > this) {
            return superAddents.toTypedArray()
        } else if (num == 1) {
            addents = arrayOf(this)
            superAddents.add(addents)
            return superAddents.toTypedArray()
        } else if (num == this) {
            addents = Array(this, { 1 })
            superAddents.add(addents)
            return superAddents.toTypedArray()
        }
        //TODO:Всё равно неправильно
        addents = Array(num + 1, { 1 })
        addents[0] = this
        superAddents.add(addents.sliceArray(0 until num))
        while (addents.last() != 1) {
            var ind = 0
            for (i in this - 1 downTo 1)
                if (addents[i] > 1) {
                    ind = i
                    break
                }
            addents[ind]--
            addents[ind + 1]++
            var sum = 0
            for (i in ind + 1 until this) {
                sum += addents[i]
                addents[i] = 0
            }
            for (i in ind + 1 until this) {
                if (addents[i - 1] < sum) {
                    addents[i] = addents[i - 1]
                    sum -= addents[i - 1]
                } else {
                    addents[i] = sum
                    break
                }
            }
            if (addents[num - 1] != 0 && addents[num] == 0) {
                val arr = addents.sliceArray(0 until num)
                superAddents.add(arr)
            }
        }
        return superAddents.toTypedArray()
    }


    for (i in 1 until num) {
        val addents = num.splitOnAddents(i)
        if (addents.any { it.all { it.checkForSquare() } }) {
            return i
        }
    }
    return num
}


fun main(argv: Array<String>) {
    println(if (checkForCross(arrayOf(0, 0, 2, 2), arrayOf(1, 1, 3, 3))) "true" else "false")
    println(if (checkForCross(arrayOf(0, 0, 1, 1), arrayOf(1, 0, 2, 1))) "true" else "false")
    println(findSumOfSquares(12))
}