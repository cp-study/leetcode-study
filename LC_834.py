from typing import List


class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        graph = {0: []}
        for i in range(len(edges)):
            if edges[i][0] not in graph:
                graph[edges[i][0]] = [edges[i][1]]
            else:
                graph[edges[i][0]].append(edges[i][1])
            if edges[i][1] not in graph:
                graph[edges[i][1]] = [edges[i][0]]
            else:
                graph[edges[i][1]].append(edges[i][0])

        count = [1] * n
        distance = [0] * n

        def cal_count(node, parent):
            for child in graph[node]:
                if child != parent:
                    cal_count(child, node)
                    count[node] += count[child]
                    distance[node] += distance[child] + count[child]

        cal_count(0, -1)

        def cal_distance(node, parent):
            for child in graph[node]:
                if child != parent:
                    distance[child] = distance[node] - count[child] + n - count[child]
                    cal_distance(child, node)

        cal_distance(0, -1)

        return distance
