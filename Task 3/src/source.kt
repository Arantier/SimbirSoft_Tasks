package Tasks

import java.util.TreeSet
import kotlin.math.min


fun generatePermutations() {
    val num = readLine()?.toInt() ?: return
    var result: ArrayList<Array<Int>> = ArrayList()
    var input = Array(num, { 0 })
    val s = readLine()?.split(" ") ?: return
    for (i in 0 until num) {
        input[i] = s[i].toInt()
    }
    input.sort()
    result.add(input.clone())
    while (true) {
        var i = input.size - 2
        while (i != -1 && (input[i] >= input[i + 1])) {
            i--
        }
        if (i == -1) {
            break
        }
        var j = input.size - 1
        while (input[i] >= input[j]) {
            j--
        }
        input[i] = input[j].also { input[j] = input[i] }
        var l = i + 1
        var r = input.size - 1
        while (l < r) {
            input[l] = input[r].also { input[r] = input[l] }
            l++
            r--
        }
        result.add(input.clone())
    }
    for (arr in result) {
        println(arr.joinToString(" "))
    }
}


//Этот алгоритм ужасно неэффективен. Но я уже лишнюю неделю мариную задачи. Пускай это будет хотя бы набросок
fun findRange(arrays: Array<Array<Int>>) {
    var minDistance = Integer.MAX_VALUE
    val grid = Array(arrays.size, { 0 })
    var start = 0
    var end = 0

    fun stupidUneffectiveFunctionToPassArrays(pos: Int) {
        for (el in arrays[pos]) {
            grid[pos] = el
            if (pos == (arrays.size - 1)) {
                val localStart = grid.min()!!
                val localEnd = grid.max()!!
                val localDistance = localEnd - localStart
                if (localDistance<minDistance){
                    start = localStart
                    end = localEnd
                    minDistance = localDistance
                }
            } else {
                stupidUneffectiveFunctionToPassArrays(pos + 1)
            }
        }
    }

    stupidUneffectiveFunctionToPassArrays(0)
    println("[$start,$end]")
}


fun maxRemoveableVertexes() {
    var num = readLine()?.toInt() ?: return
    //Основные массивы
    var tree: Array<ArrayList<Int>> = Array(num, { ArrayList<Int>() })
    var visited = Array(num, { false })
    var subtreesSizes = Array(num, { 0 })

    //Поиск в глубину c подсчётом размеров поддеревьев для каждой вершины
    fun countSizes(vertex: Int): Int {
        visited[vertex] = true
        var subsizesNumber = 0
        for (i in tree[vertex]) {
            if (!visited[i]) {
                subsizesNumber += countSizes(i)
            }
        }
        subtreesSizes[vertex] = subsizesNumber + 1
        return subsizesNumber + 1
    }

    //Основная часть
    for (i in 0 until (num - 1)) {
        val input = (readLine() ?: return).split(" ")
        val a = input[0].toInt() - 1
        val b = input[1].toInt() - 1
        tree[a].add(b)
        tree[b].add(a)
    }
    var answer = 0
    if (num % 2 == 1) {
        answer = -1
    } else {
        countSizes(0)
        for (i in 0 until num) {
            if (subtreesSizes[i] % 2 == 0) {
                answer++
            }
        }
        answer--
    }
    println(answer)
}

fun main(argc: Array<String>) {
    generatePermutations()


    findRange(arrayOf(arrayOf(4, 10, 15, 24, 26), arrayOf(0, 9, 12, 20), arrayOf(5, 18, 22, 30)))

    maxRemoveableVertexes()
}

