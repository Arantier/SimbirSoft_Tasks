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
    fun recursiveDropPairs(arr: ArrayList<Int>): Int {
        if (arr.isEmpty()) {
            return 0
        } else {
            if (arr[0] == arr[1]) {
                return recursiveDropPairs(arr.subList(2, arr.size) as ArrayList<Int>)
            } else if (arr[arr.size - 2] == arr.last()) {
                return recursiveDropPairs(arr.subList(0, arr.size - 2) as ArrayList<Int>)
            }
            for (i in 1 until arr.size - 2) {
                if (arr[i] == arr[i + 1]) {
                    arr.remove(arr[i])
                    return min(i, arr.size - i + 1) + recursiveDropPairs(arr)
                }
            }
            var distance = arr.size
            for (i in 1 until arr.size) {
                val e1 = arr.indexOfLast { it == arr[i] }
                val s2 = arr.indexOfFirst { it == arr[i - 1] }
                if (i == e1 && (i - 1) == s2) {
                    val s1 = arr.indexOfFirst { it == arr[i] }
                    val e2 = arr.indexOfLast { it == arr[i - 1] }
                    val newDist = e1 - s1 + e2 - s2
                    if (newDist < distance) {
                        distance = newDist
                    }
                }
            }

        }
        return -1
    }

    val num = readLine()?.toInt() ?: return
    val rawPairs = readLine()?.split(" ") ?: return
    val pairs = Array(num * 2, { it -> rawPairs[it].toInt() })


}


fun main(argv: Array<String>) {
    println(isPalindrome(123321))
    println(checkForSplit(arrayListOf(2, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10)))
}