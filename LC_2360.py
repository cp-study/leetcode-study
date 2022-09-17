from collections import deque
from typing import List


class Solution:
    def longestCycle(self, edges: List[int]) -> int:
        n = len(edges)
        level = [-1] * n
        parent = [-1] * n

        def bfs(start):
            queue = deque()
            queue.append(start)
            level[start] = 0
            parent[start] = start

            while queue:
                node = queue.popleft()
                next_node = edges[node]

                if edges[node] == -1:
                    return -1

                if level[next_node] != -1:
                    if parent[next_node] != parent[node]:
                        return -1
                    return level[node] - level[next_node] + 1

                level[next_node] = level[node] + 1
                parent[next_node] = start
                queue.append(next_node)

            return -1

        ans = -1
        for i in range(n):
            if edges[i] != -1 and level[i] == -1:
                ans = max(ans, bfs(i))
        return ans
