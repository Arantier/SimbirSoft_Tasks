import kotlin.math.min

fun findMinPositive(numbers: Array<Int>?): Int {
    var target = 1
    if (numbers == null) {
        return target
    }
    while (numbers.contains(target)) {
        target++
    }
    return target
}


//Тип???
fun <T> mergeAndSort(left: Array<T>, vararg right: Array<T>): Array<T> {
    //Можно было бы отсортировать массив и вставками добавить в него
    //элементы из другого массива, но мощность - nlogn+n^2
    //А объединение с последующей сортировкой займёт, по моим рассчётам
    //2nLogn операций, не считая объединения. Учитывая то, что мой код
    //написан студентом третьего курса за десять минут, а код функций
    // сортировки и объединения - специалистами JetBrains за всяко больший
    // период времени - выбор очевиден
    var res: Array<T> = left
    for (arr in right) {
        res += arr
    }
    res.sort()
    return res
}


fun legushka(dest: Int, distance: Int, roadmapRaw: Long): Int {
    var roadmap: ArrayList<Int> = ArrayList(roadmapRaw.toString().length)
    var acc = roadmapRaw
    while (acc > 1) {
        roadmap.add(acc.rem(10).toInt())
        acc /= 10
    }
    roadmap.add(acc.toInt())
    roadmap.reverse()

    return legushkaAdvanced(0, dest - 1, distance, roadmap)
}

tailrec fun legushkaAdvanced(start: Int, dest: Int, distance: Int, roadmap: ArrayList<Int>, acc: Int = 0): Int {
    var step = min((distance + start), roadmap.size - 1)
    while ((step != start) && (roadmap[step] == 0)) {
        step--
    }
    return if (step == start) {
        -1
    } else if (step == dest) {
        acc + 1
    } else {
        legushkaAdvanced(step, dest, distance, roadmap, acc + 1)
    }
}


fun main(args: Array<String>) {
    val minPoz1 = findMinPositive(arrayOf(1, 2, 0))
    println("Наименьшее пропущенное число:$minPoz1")
    val minPoz2 = findMinPositive(arrayOf(3, 4, -1, 1))
    println("Наименьшее пропущенное число:$minPoz2")
    val minPoz3 = findMinPositive(arrayOf(7, 8, 9, 11, 12))
    println("Наименьшее пропущенное число:$minPoz3")

    val arr = mergeAndSort(arrayOf(1, 4, 5), arrayOf(1, 3, 4), arrayOf(2, 6))
    println("Конечный массив: ${arr.joinToString("\t")}")

    var leg1: Int = legushka(8, 4, 10010101)
    println("Лягушке потребуется " + if (leg1 > 0) "$leg1 шагов(а)" else " найти другой дом")
    var leg2: Int = legushka(4, 2, 1001)
    println("Лягушке потребуется " + if (leg2 > 0) "$leg2 шагов(а)" else " найти другой дом")
    var leg3: Int = legushka(8, 4, 11100101)
    println("Лягушке потребуется " + if (leg3 > 0) "$leg3 шагов(а)" else " найти другой дом")
    var leg4: Int = legushka(12, 3, 101111100101)
    println("Лягушке потребуется " + if (leg4 > 0) "$leg4 шагов(а)" else " найти другой дом")
}