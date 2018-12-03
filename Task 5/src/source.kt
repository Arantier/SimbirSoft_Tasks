import kotlin.math.pow
import kotlin.math.sqrt


fun checkForCross(rect1: Array<Int>, rect2: Array<Int>): Boolean {
    return (((rect1[0] in (rect2[0] + 1) until rect2[2]) || (rect1[2] in (rect2[0] + 1) until rect2[2]))
            && ((rect1[1] in (rect2[1] + 1) until rect2[3]) || (rect1[3] in (rect2[1] + 1) until rect2[3])))
}


fun findSumOfSquares(num: Int): Int {

    fun Int.splitOnTerms(qTerms: Int): Array<Array<Int>> {
        val partitions = ArrayList<Array<Int>>()
        val a = Array(num, { 0 })

        fun rec(k: Int, n: Int, v: Int = n) {
            if (n < k) {
                return
            }
            if (k == 1) {
                if (n > v) {
                    return
                }
                a[0] = n
                val p = ArrayList<Int>()
                for (el in a) {
                    if (el != 0) {
                        p.add(el)
                    }
                }
                partitions.add(p.toTypedArray())
                return
            }
            a[k - 1] = if (n < v) n else v
            while (a[k - 1] > 0) {
                rec(k - 1, n - a[k - 1], a[k - 1])
                a[k - 1]--
            }
        }

        rec(qTerms, this)
        return partitions.toTypedArray()
    }

    fun checkForSquare(a: Int): Boolean {
        return (sqrt(a.toDouble()).toInt().toDouble().pow(2).toInt() == a)
    }

    for (i in 1 until num) {
        val p = num.splitOnTerms(i)
        if (p.any { it.all { checkForSquare(it) } }) {
            return i
        }
    }
    return num
}


fun learningLanguages(emp: Array<Array<Int>>) {
    val adjList = Array<ArrayList<Int>>(emp.size,{ ArrayList() })
    for (i in 0 until (emp.size-1)) {
        for (j in (i+1) until emp.size) {
            if (emp[i].any { emp[j].contains(it) }) {
                adjList[i].add(j)
                adjList[j].add(i)
            }
        }
    }

    val freeVert = Array(emp.size, { true })

    fun dfs(v:Int){
        freeVert[v] = false
        for (next in adjList[v]){
            if (freeVert[next]){
                dfs(next)
            }
        }
    }

    var nLang = 0
    for (i in 0 until freeVert.size){
        if (freeVert[i]){
            nLang++
            dfs(i)
        }
    }
    nLang--
    println("$nLang")
    return
}


fun main(argv: Array<String>) {
    println(if (checkForCross(arrayOf(0, 0, 2, 2), arrayOf(1, 1, 3, 3))) "true" else "false")
    println(if (checkForCross(arrayOf(0, 0, 1, 1), arrayOf(1, 0, 2, 1))) "true" else "false")

    println(findSumOfSquares(2000))

    learningLanguages(arrayOf(arrayOf(0),
            arrayOf(1, 2, 3),
            arrayOf(1),
            arrayOf(5, 4),
            arrayOf(6, 7),
            arrayOf(3),
            arrayOf(7, 4),
            arrayOf(1)))
    learningLanguages(arrayOf(
            arrayOf(2),
            arrayOf(2,3),
            arrayOf(3,4),
            arrayOf(4,5),
            arrayOf(5)
    ))
    learningLanguages(arrayOf(
            arrayOf(2),
            arrayOf()
    ))
}
