import kotlin.contracts.contract
import kotlin.math.min

class LC_120 {

    lateinit var mem: MutableList<MutableList<Int>>
    fun minimumTotal(triangle: List<List<Int>>): Int {
        mem = mutableListOf()

        for (i in triangle.indices) {
            if (i==0) mem.add(mutableListOf(triangle[0][0]))
            for (j in 0..triangle[i].size - 2) {
                val upper = mem[i-1][j]
                when {
                    j == 0 -> {
                        val initRow = mutableListOf<Int>()
                        mem.add(initRow)
                        initRow.addAll(listOf(triangle[i][0]+upper, triangle[i][1]+upper))
                    }
                    else -> {
                        val curRow = mem.last()
                        curRow[curRow.lastIndex] = min(curRow.last(), triangle[i][j] + upper)
                        curRow.add(triangle[i][j+1] + upper)
                    }
                }
            }
        }
        return mem[mem.size.minus(1)].min()
    }
}

fun main() {
    val triangle = listOf(listOf(2), listOf(3,4), listOf(6,5,7), listOf(4,1,8,3))
    val sol = LC_120().minimumTotal(triangle)
    println(sol)
}