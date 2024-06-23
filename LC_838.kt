class LC_838 {
    fun pushDominoes(dominoes: String): String {
        val n = dominoes.length
        val forces = IntArray(n)
        var force = 0
        for (i in 0 until n) {
            if (dominoes[i] == 'R') force = n
            else if (dominoes[i] == 'L') force = 0
            else force = maxOf(force - 1, 0)
            forces[i] += force
        }
        force = 0
        for (i in n - 1 downTo 0) {
            if (dominoes[i] == 'L') force = n
            else if (dominoes[i] == 'R') force = 0
            else force = maxOf(force - 1, 0)
            forces[i] -= force
        }
        return forces.map { if (it > 0) 'R' else if (it < 0) 'L' else '.' }.joinToString("")
    }
}

fun main() {
    val sol = LC_838()
    sol.pushDominoes("RR.L").also {
        println(it)
    }
}