import heapq
from typing import List


class Solution:
    def mincostToHireWorkers(self, quality: List[int], wage: List[int], k: int) -> float:
        workers = sorted([float(w) / q, q] for w, q in zip(wage, quality))
        result = float('inf')
        qsum = 0
        heap = []

        for r, q in workers:
            heapq.heappush(heap, -q)
            qsum += q

            if len(heap) > k:
                qsum += heapq.heappop(heap)

            if len(heap) == k:
                result = min(result, qsum * r)
        return result
