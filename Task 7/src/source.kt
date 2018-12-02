import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

fun isPalindrome(num: Int): Boolean {
    return isPalindrome(num.toString())
}


fun isPalindrome(str: String): Boolean {
    return if (str.length > 1 && str.first() == str.last()) {
        isPalindrome(str.substring(1, str.length - 1))
    } else {
        (str.length <= 1)
    }
}


fun checkForSplit(arr: ArrayList<Int>,
                  leftSum: Int = 0,
                  rightSum: Int = 0): Boolean {
    if (arr.isEmpty()) {
        return leftSum == rightSum
    } else {
        val new = arr.max()!!
        arr.remove(new)
        //Поскольку arr,max==null <=> arr.size==0, то можно рискнуть
        return if (leftSum > rightSum) {
            checkForSplit(arr, leftSum, rightSum + new)
        } else {
            checkForSplit(arr, leftSum + new, rightSum)
        }
    }
}


fun allenGoneMad() {
    val num = readLine()?.toInt() ?: return
    val rawPairs = readLine()?.split(" ") ?: return
    val pairs = ArrayList<Int>()
    for (i in rawPairs) {
        pairs.add(i.toInt())
    }
    var counter = 0

    fun dropFreePairs() {
        if (pairs[0] == pairs[1]) {
            pairs.remove(pairs.first())
            pairs.remove(pairs.first())
        } else if (pairs[pairs.size - 2] == pairs.last()) {
            pairs.remove(pairs.last())
            pairs.remove(pairs.last())
        }

        for (i in 0 until pairs.size / 2) {
            if (pairs[2 * i] == pairs[2 * i + 1]) {
                counter += 2 * min(2 * i, pairs.size - 2 * i - 1)
                pairs.remove(pairs[2 * i])
                pairs.remove(pairs[2 * i])
                break
            }
        }
    }

    while (pairs.size > 0)
        if (pairs.isEmpty()) {
            println(0)
        } else {
            dropFreePairs()

            //TODO: Додумай алгоритм
            // Дальше остаётся последовательность без правильных пар.
            // Какого-то внятного алгоритма придумать не выходит.
        }


}


fun main(argv: Array<String>) {
    println(isPalindrome(123321))
    println(checkForSplit(arrayListOf(2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10)))
    allenGoneMad()
}