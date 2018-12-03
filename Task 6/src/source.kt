fun checkForSquare(num: Int): Boolean {
    var x = num.toDouble()
    var xNext = 0.5 * (x + num / x)
    val eps = 1e-5
    var delta = if (xNext > x) xNext - x else x - xNext
    while (delta > eps) {
        x = xNext
        xNext = 0.5 * (x + num / x)
        delta = if (xNext > x) xNext - x else x - xNext
    }
    val root = xNext.toInt()
    return (root * root == num)
}

fun Array<Int>.removeMembersWith2inEndAndMultipleOn2(): Array<Int> {
    //А в Си бы это называлось rmw2ieam, было бы не расширением,
    //а утилитой и бесило бы всех, кроме создателя...
    var result = ArrayList<Int>()
    this.forEach {
        if ((it - 1) * 2 % 10 != 0) {
            result.add(it * 2)
        }
    }
    return result.toTypedArray()
}


fun grandTheftKeyboard() {
    val num = readLine()?.toInt() ?: return
    val input = readLine()?.split(" ") ?: return
    val arr = Array(num, { i -> input[i].toInt() }).sortedArray()
    var stolenKeyboards = 0
    for (i in arr.first()..arr.last()){
        if (!arr.contains(i)){
            stolenKeyboards++
        }
    }
    println(stolenKeyboards)
}


fun main(argv: Array<String>) {
    for (i in 0..2000) {
        println(checkForSquare(i * i))
    }

    println(arrayOf(1, 2, 3).removeMembersWith2inEndAndMultipleOn2()
            .joinToString(" "))
    println(arrayOf(2, 6, 11).removeMembersWith2inEndAndMultipleOn2()
            .joinToString(" "))
    println(arrayOf(0).removeMembersWith2inEndAndMultipleOn2()
            .joinToString(" "))

    grandTheftKeyboard()
}