from typing import List
import heapq


class Solution:
    def furthestBuilding(self, heights: List[int], bricks: int, ladders: int) -> int:
        n = len(heights)
        heap = []
        heapq.heapify(heap)

        for i in range(1, n):
            diff = heights[i] - heights[i-1]
            if diff <= 0:
                continue
            else:
                if len(heap) < ladders:
                    heapq.heappush(heap, diff)
                elif heap and heap[0] < diff:
                    use_bricks = heapq.heappop(heap)
                    heapq.heappush(heap, diff)
                    bricks -= use_bricks
                    if bricks < 0:
                        return i-1
                else:
                    bricks -= diff
                    if bricks < 0:
                        return i-1
        return n-1
