from typing import List


class Solution:
    def largestPathValue(self, colors: str, edges: List[List[int]]) -> int:

        graph = {}
        indegree = [0] * len(colors)
        for edge in edges:
            graph[edge[0]] = graph.get(edge[0], []) + [edge[1]]
            indegree[edge[1]] += 1
        color_cnt = [[0] * 26 for i in range(len(colors))]
        queue = []

        for i in range(len(colors)):
            if indegree[i] == 0:
                queue.append(i)

        max_value = 0
        processed = 0
        while queue:
            processed += 1
            node = queue.pop(0)

            color_cnt[node][ord(colors[node]) - ord('a')] += 1

            for child in graph.get(node, []):
                indegree[child] -= 1
                for j in range(26):
                    color_cnt[child][j] = max(color_cnt[child][j], color_cnt[node][j])

                if indegree[child] == 0:
                    queue.append(child)

            max_value = max(max_value, max(color_cnt[node]))

        return max_value if processed == len(colors) else -1
