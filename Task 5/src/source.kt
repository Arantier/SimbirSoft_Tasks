import kotlin.math.sqrt

fun checkForCross(rect1: Array<Int>, rect2: Array<Int>): Boolean {
    return (((rect1[0] in (rect2[0]+1) until rect2[2]) || (rect1[2] in (rect2[0] +1) until rect2[2]))
            && ((rect1[1] in (rect2[1]+1) until rect2[3]) || (rect1[3] in (rect2[1]+1) until rect2[3])))
}


fun findSquares(num:Int):Int{

    fun findDividers(num:Int):Pair<ArrayList<Int>,ArrayList<Int>>{
        var simpleDividers = Pair(ArrayList<Int>(),ArrayList<Int>())
        for (i in 2..sqrt(num.toDouble()).toInt()){
            if (num%i==0){
                simpleDividers.first.add(i)
                simpleDividers.second.add(num/i)
            }
        }
        return simpleDividers
    }

    if (sqrt(num.toDouble()).toInt()*sqrt(num.toDouble()).toInt() == num){
        return 1
    }

    val dividers = findDividers(num)


    return 0
}


fun main(argv: Array<String>) {
    println(if (checkForCross(arrayOf(0, 0, 2, 2), arrayOf(1, 1, 3, 3))) "true" else "false")
    println(if (checkForCross(arrayOf(0, 0, 1, 1), arrayOf(1, 0, 2, 1))) "true" else "false")
    findSquares(12)
}