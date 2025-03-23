class LC_36 {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (i in board.indices) {
            val set = mutableSetOf<Char>()
            for (j in board[i]) {
                when (j) {
                    '.' -> continue
                    in set -> return false
                    !in set -> set.add(j)
                }
            }
        }
        for (i in 0..<board.first().size) {
            val set = mutableSetOf<Char>()
            for (j in 0..<board.size) {
                when (val cur = board[j][i]) {
                    '.' -> continue
                    in set -> return false
                    !in set -> set.add(cur)
                }
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                val g = listOf(0,1,2,3,4,5,6,7,8).groupBy { it/3 }
                val (rx, ry) = Pair(g[i]!!.min(),g[i]!!.max())
                val (cx, cy) = Pair(g[j]!!.min(),g[j]!!.max())
                if (!validSubBox(board, rx, ry, cx, cy)) return false
            }
        }
        return true
    }

    private fun validSubBox(board: Array<CharArray>, rx: Int, ry: Int, cx: Int, cy: Int): Boolean {
        val set = mutableSetOf<Char>()
        for (r in rx..ry) {
            for (c in cx..cy) {
                when (val cur = board[r][c]) {
                    '.' -> continue
                    in set -> return false
                    !in set -> set.add(cur)
                }
            }
        }
        return true
    }
}
