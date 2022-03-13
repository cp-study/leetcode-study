package main

class LC1697 {
    fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
        val nEdges = edgeList.size
        val nQueries = queries.size

        val queryArr = Array(nQueries) { idx ->
            val (from, to, distance) = queries[idx]
            Query(from, to, distance, idx)
        }
        //쿼리별 간선의 길이의 오름차순으로 정렬
        queryArr.sortBy { it.distance }
        //정점간의 간선의 길이의 오름차순으로 정렬
        edgeList.sortBy{ it[2] }
        //n크기의 유니온파인드 집합초기화
        val uf = UnionFind(n)

        val ans = BooleanArray(nQueries){ false }

        var idxEdge = 0
        //정렬된 쿼리 순으로 수행함
        for((queryFrom, queryTo, queryDistance, idxQuery) in queryArr){
            while(idxEdge < nEdges){
                val (edgeFrom, edgeTo, edgeWeight) = edgeList[idxEdge]
                if(edgeWeight >= queryDistance) break
                uf.union(edgeFrom, edgeTo)
                ++idxEdge
            }
            //처리는 정렬순이지만 ans는 idx
            ans[idxQuery] = uf.find(queryFrom) == uf.find(queryTo)
        }

        return ans
    }

    private class UnionFind(capacity: Int){
        private val roots = IntArray(capacity){ idx -> idx }
        private val ranks = IntArray(capacity){ 1 }

        fun union(x: Int, y: Int){
            val rootX = find(x)
            val rootY = find(y)

            if(rootX == rootY) return

            if(ranks[rootX] > roots[rootY]){
                roots[rootY] = rootX
                ++ranks[rootX]
            }else{
                roots[rootX] = rootY
                ++ranks[rootY]
            }
        }

        fun find(x: Int): Int{
            if(roots[x] != x){
                roots[x] = find(roots[x])
            }

            return roots[x]
        }
    }

    private data class Query(val from: Int, val to: Int, val distance: Int, val idx: Int)
}
