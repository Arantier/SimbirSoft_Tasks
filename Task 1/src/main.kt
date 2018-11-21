import java.util.TreeSet
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun findMaxPrefix(arrString: Array<String>?): String {
    var maxPrefix = ""
    if ((arrString != null) && (arrString.size > 0)) {
        var flag = true
        var pos = -1
        while (flag) {
            pos++
            for (i in 1..(arrString.size - 1)) {
                if (arrString[0].toLowerCase()[pos] != arrString[i].toLowerCase()[pos]) {
                    flag = false
                    break
                }
            }
        }
        if (pos > 0) {
            maxPrefix = arrString.first().toLowerCase().substring(0, pos)
        }
    }
    return maxPrefix
}

fun regexps(string: String, pattern: String): Boolean {
    //Поскольку в задаче виды строк ограничены, то я счёл самым простым использовать обычные регулярки
    //ни проверяя их на всякие каверзные регулярки, ни реализуя более частную, но сложную функцию для этой задачи
    //Нечего мозги на первых же задачах перенапрягать)
    val realPattern: Pattern = Pattern.compile(pattern.replace("?", ".")
            .replace("*", ".*"))
    return string.matches(realPattern.toRegex())
}

fun sumArea(arrayOfRects: Array<Array<Int>>): Int {
    var X: TreeSet<Int> = TreeSet()
    var Y: TreeSet<Int> = TreeSet()
    for (rect in arrayOfRects) {
        X.add(rect[0])
        X.add(rect[2])
        Y.add(rect[1])
        Y.add(rect[3])
    }
    var area = 0
    for (i in 0..(X.size - 2)) {
        for (j in 0..(Y.size - 2)) {
            var center: Pair<Double, Double> = Pair(
                    X.elementAt(i) + (X.elementAt(i + 1) - X.elementAt(i)) / 2.0,
                    Y.elementAt(j) + (Y.elementAt(j + 1) - Y.elementAt(j)) / 2.0)
            for (rect in arrayOfRects) {
                if (((center.first >= rect[0]) && (center.first <= rect[2]))
                        && ((center.second >= rect[1]) && center.second <= rect[3])) {
                    area += (X.elementAt(i + 1) - X.elementAt(i)) * (Y.elementAt(j + 1) - Y.elementAt(j))
                    break
                }
            }
        }
    }
    return area
}

fun main(args: Array<String>) {
    var strings = arrayOf("Префикс", "преграда", "Противень")
    println("Префикс:" + findMaxPrefix(strings))
    strings = strings.plus("Павел")
    println("Префикс:" + findMaxPrefix(strings))
    strings = strings.plus("Облом")
    println("Префикс:" + findMaxPrefix(strings))
    println("Префикс:" + findMaxPrefix(null))

    val reg1 = "start*end"
    val reg2 = "e??*?????"
    val reg3 = "?????*?????"
    strings = arrayOf("startYOUSHALLPASSend", "endYOUSHALLNOTPASSstart")
    for ((index, s) in strings.withIndex()) {
        println("Строка №$index " + (if (regexps(s, reg1)) "прошла" else "не прошла") + " первую регулярку")
        println("Строка №$index " + (if (regexps(s, reg2)) "прошла" else "не прошла") + " вторую регулярку")
        println("Строка №$index " + (if (regexps(s, reg3)) "прошла" else "не прошла") + " третью регулярку")
    }

    var figureArea = sumArea(arrayOf(arrayOf(0, 0, 1, 1), arrayOf(1, 1, 2, 2)))
    println("Площадь фигуры из прямоугольников:$figureArea")
    var figureArea2 = sumArea(arrayOf(arrayOf(0, 0, 3, 2),
            arrayOf(3, 1, 8, 5),
            arrayOf(6, 0, 10, 4),
            arrayOf(2, 2, 3, 4),
            arrayOf(0, 3, 11, 7)))
    println("Площадь фигуры из прямоугольников:$figureArea2")
    var figureArea3 = sumArea(arrayOf(
            arrayOf(0, 0, 10, 10),
            arrayOf(0, 0, 10, 10),
            arrayOf(0, 0, 10, 10),
            arrayOf(0, 0, 10, 10),
            arrayOf(0, 0, 10, 10)
    ))
    println("Площадь фигуры из прямоугольников:$figureArea3")
}