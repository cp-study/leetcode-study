class Solution:
    def buildMatrix(self, k: int, rowConditions: List[List[int]], colConditions: List[List[int]]) -> List[List[int]]:

        rowGraph = defaultdict(list)
        rowIndegree = [0] * (k + 1)
        colGraph = defaultdict(list)
        colIndegree = [0] * (k + 1)

        for rc in rowConditions:
            rowGraph[rc[0]].append(rc[1])
            rowIndegree[rc[1]] += 1

        for cc in colConditions:
            colGraph[cc[0]].append(cc[1])
            colIndegree[cc[1]] += 1

        def topologicalSort(graph, indegree):
            ret = []
            queue = deque()

            for i in range(1, k + 1):
                if indegree[i] == 0:
                    queue.append(i)

            # cycle!
            if len(queue) == 0:
                return ret

            while len(queue) > 0:
                node = queue.popleft()
                ret.append(node)

                for next in graph[node]:
                    indegree[next] -= 1

                    if indegree[next] == 0:
                        queue.append(next)

            return ret

        rowsort = topologicalSort(rowGraph, rowIndegree)
        colsort = topologicalSort(colGraph, colIndegree)

        if len(rowsort) != k or len(colsort) != k:
            return []

        rpos = [0] * (k+1)
        for i, r in enumerate(rowsort):
            rpos[r] = i

        ans = [[0] * k for _ in range(k)]
        for i, c in enumerate(colsort):
            ans[rpos[c]][i] = c
        return ans
