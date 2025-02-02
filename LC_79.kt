class LC_79 {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        // 모든 시작 좌표에 대해 DFS 탐색을 수행
        for (i in board.indices) {
            for (j in board[0].indices) {
                val visited = mutableSetOf<Pair<Int, Int>>()
                // 시작 위치를 visited에 추가하고 첫 글자부터 탐색 시작
                visited.add(Pair(i, j))
                if (dfs(board, i, j, visited, board[i][j].toString(), word)) return true
            }
        }
        return false
    }

    private fun dfs(
        board: Array<CharArray>,
        i: Int,
        j: Int,
        visited: MutableSet<Pair<Int, Int>>,
        cur: String,
        word: String,
    ): Boolean {
        println(cur)
        if (cur == word) return true
        if (!word.startsWith(cur)) return false
        for (d in direction) {
            val ni = i + d[0]
            val nj = j + d[1]
            // 범위를 벗어나면 해당 방향은 건너뛰기
            if (ni < 0 || ni >= board.size || nj < 0 || nj >= board[0].size) continue
            val nextPos = Pair(ni, nj)
            // 이미 방문한 좌표라면 건너뛰기
            if (nextPos in visited) continue

            visited.add(nextPos)
            if (dfs(board, ni, nj, visited, cur + board[ni][nj], word)) return true
            visited.remove(nextPos)
        }
        return false
    }

    companion object {
        val direction = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1),
        )
    }
}
fun boardOf(vararg rows: String): Array<CharArray> =
    rows.map { it.toCharArray() }.toTypedArray()


fun main(args: Array<String>) {
    val board = boardOf("ABCE", "SFCS", "ADEE")
    val word = "SEE"
    val sol = LC_79()
    println(sol.exist(board, word))
}