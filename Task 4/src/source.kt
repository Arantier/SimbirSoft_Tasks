import kotlin.math.max

fun findMaxSubArray(arr: Array<Int>): Int {
    var sum = 0
    var ans = arr[0]
    for (el in arr) {
        sum += el
        ans = max(ans, sum)
        sum = max(sum, 0)
    }
    return ans
}

fun decode(input: String): String {
    var openBracketPosition = input.indexOfFirst { it == '[' }
    while (openBracketPosition > 0 && !input[openBracketPosition - 1].isDigit()) {
        openBracketPosition = input.substring((openBracketPosition + 1)..(input.length - 1)).indexOfFirst { it == '[' }
    }
    if (openBracketPosition < 0) {
        return input
    }

    var indicator = 1
    var position = openBracketPosition + 1
    while (indicator != 0 && position < input.length) {
        if (input[position] == '[') {
            indicator++
        } else if (input[position] == ']') {
            indicator--
        }
        position++
    }
    val closeBracketPosition = position - 1
    if (input[closeBracketPosition] != ']') {
        return input
    }

    val stringToMultiply = input.substring(openBracketPosition + 1, closeBracketPosition)
    var numInString = ""
    var pos = openBracketPosition - 1
    while (pos >= 0 && input[pos].isDigit()) {
        numInString += input[pos]
        pos--
    }
    val multiplier = numInString.reversed().toInt()

    var output = input.substring(0, pos + 1)
    for (i in 0 until multiplier) {
        output += stringToMultiply
    }
    output += input.substring(closeBracketPosition + 1, input.length)
    return output
}

fun decoder(input: String): String {
    var output = decode(input)
    var nextOutput = decode(output)
    while (output.compareTo(nextOutput) != 0) {
        output = nextOutput
        nextOutput = decode(nextOutput)
    }
    return output
}


fun canItSort(): String {
    val num = readLine()?.toInt() ?: return "NO"
    val inp = readLine()?.split(" ") ?: return "NO"
    var array = ArrayList<Int>()
    for (i in inp){
        array.add(i.toInt())
    }
    val finalArray = array.sorted()
    val strPosToSort = "${readLine() ?: return "NO"}0"
    var subArrToSort = ArrayList<Int>()
    var start = 0
    for (i in 0 until num){
        if (strPosToSort[i]=='1'){
            if (i==0 || strPosToSort[i-1]=='0'){
                start = i
            }
            subArrToSort.add(array[i])
            if (strPosToSort[i+1]=='0'){
                subArrToSort.add(array[i+1])
                subArrToSort.sort()
                for (j in 0 until subArrToSort.size){
                    array[start+j] = subArrToSort[j]
                }
                subArrToSort.clear()
            }
        }
    }
    return if (finalArray == array)"YES" else "NO"
}


fun main(argv: Array<String>) {
    println(findMaxSubArray(arrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))

    println(decoder("3[a]2[bc]"))
    println(decoder("3[a2[c]]"))
    println(decoder("2[abc]3[cd]ef"))

    println(canItSort())
}