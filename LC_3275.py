from typing import List
import heapq

class Solution:
    def resultsArray(self, queries: List[List[int]], k: int) -> List[int]:
        ans = []

        heap_data = []

        for q in queries:
            dist = abs(q[0]) + abs(q[1])
            heapq.heappush(heap_data, -dist)
            if len(heap_data) > k:
                heapq.heappop(heap_data)

            if len(heap_data) != k:
                ans.append(-1)
            else:
                ans.append(-heap_data[0])

        return ans
