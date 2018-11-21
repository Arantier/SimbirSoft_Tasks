package Tasks


fun generatePermutations(arr: Array<Int>): ArrayList<Array<Int>> {
    var result: ArrayList<Array<Int>> = ArrayList()
    var input = arr
    input.sort()
    input.reverse()
    var sequences = Array(arr.size, { 0 })
    var directions = Array(arr.size, { 1 })
    var flag = true
    while (flag) {
        result.add(input)
        var i = input.size - 1
        while ( (i > 0) && ( ( (directions[i] == 1) && (sequences[i] == i))
                        || ( (directions[i] == -1) && (sequences[i] == 0) ) ) ) {
            i--
        }
        flag = i > 0
        if (flag) {
            sequences[i] += directions[i]
            for (j in (i + 1) until input.size) {
                directions[j] = -directions[j]
            }
            var j = input.size - 1
            while ((j > 0) && (input[j] != i + 1)) {
                j--
            }
            val buf = input[j]
            input[j] = input[j+directions[i]].also{input[j+directions[i]] = buf}
        }
    }
    return result
}


fun main(argc: Array<String>) {
    for (permutation in generatePermutations(arrayOf(1, 2, 3))) {
        println(permutation.joinToString(" "))
    }
}