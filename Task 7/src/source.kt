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

fun Int.splitOnAddents(num: Int): Array<Array<Int>> {
    var addents = ArrayList<Int>()
    var superAddents = ArrayList<Array<Int>>()
    for (i in 1..this) {
        addents.add(0)
    }
    if (num < 0 || num > this) {
        return superAddents.toTypedArray()
    } else if (num == 1) {
        addents[0] = this
        addents.removeIf { it == 0 }
        superAddents.add(addents.toTypedArray())
        return superAddents.toTypedArray()
    } else if (num == this) {
        addents.addAll(Array(num, { 1 }))
        superAddents.add(addents.toTypedArray())
        return superAddents.toTypedArray()
    }
    addents[0] = this
    while (addents[0] != 1) {
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
            var arr = addents.clone() as ArrayList<Int>
            arr.removeIf { it == 0 }
            superAddents.add(arr.toTypedArray())
        }
    }
    return superAddents.toTypedArray()
}


fun findSumOfSquares(num: Int): Int {
    for (i in 1..num){
        var addents = num.splitOnAddents(i)
        for (v in addents){
/*            if (v.all { sqrt(it.toDouble()).pow(2) == it.toDouble() }){
                return v.size
            }*/
            if (v.all{it ->
                        val a = sqrt(it.toDouble()).toInt().toDouble()
                        val b = a.pow(2).toInt()
                        val c = it
                        b == c
                    }) {
                return v.size
            }
        }
    }
    return -1
}


fun allenGoneMad(){
    fun recursiveDropPairs(arr:ArrayList<Int>):Int{
        if (arr.isEmpty()){
            return 0
        } else if (arr[0]==arr[1]){
            return recursiveDropPairs(arr.subList(2,arr.size) as ArrayList<Int>)
        } else if (arr[arr.size-2] == arr.last()){
            return recursiveDropPairs(arr.subList(0,arr.size-2) as ArrayList<Int>)
        } else {
            for (i in 1 until arr.size-2){
                if (arr[i]==arr[i+1]){
                    arr.remove(arr[i])
                    return min(i, arr.size-i+1) + recursiveDropPairs(arr)
                }
            }
            //Дальше ищешь пересечения разделённых пар с минимальными расстояниями
            //, удаляешь эти пары и добавляешь к итогу сумму расстояний - 1
            //TODO:Есть проблема с выводом
        }
    }
    val num = readLine()?.toInt() ?: return
    val rawPairs = readLine()?.split(" ") ?: return
    val pairs = Array(num*2,{it -> rawPairs[it].toInt()})


}

//TODO: Серьёзно оптимизируй код, это просто
fun main(argv: Array<String>) {
    println(isPalindrome(123321))

    println(findSumOfSquares(121312))
}