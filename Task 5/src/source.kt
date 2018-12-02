import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.truncate

fun checkForCross(rect1: Array<Int>, rect2: Array<Int>): Boolean {
    return (((rect1[0] in (rect2[0] + 1) until rect2[2]) || (rect1[2] in (rect2[0] + 1) until rect2[2]))
            && ((rect1[1] in (rect2[1] + 1) until rect2[3]) || (rect1[3] in (rect2[1] + 1) until rect2[3])))
}


fun findSumOfSquares(num: Int): Int {
   fun Int.splitOnTerms(qTerms:Int):Array<Array<Int>>{
       var arrOfTerms = ArrayList<Int>()
       var terms = Array<Int>(num,{0})

       fun rec(rest:Int,)
   }
}


fun main(argv: Array<String>) {
    println(if (checkForCross(arrayOf(0, 0, 2, 2), arrayOf(1, 1, 3, 3))) "true" else "false")
    println(if (checkForCross(arrayOf(0, 0, 1, 1), arrayOf(1, 0, 2, 1))) "true" else "false")
    println(findSumOfSquares(12))
}