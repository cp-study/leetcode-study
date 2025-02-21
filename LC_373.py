from typing import List
import heapq

class Solution:
    def kSmallestPairs(self, nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
        n = len(nums1)
        m = len(nums2)

        heap = []

        for i in range(n):
            heap.append((nums1[i]+nums2[0], i, 0))

        heapq.heapify(heap)

        ans = []
        while k and heap:
            e = heapq.heappop(heap)
            ans.append([nums1[e[1]], nums2[e[2]]])
            k -= 1
            if e[2] + 1 < m:
                heapq.heappush(heap,(nums1[e[1]] + nums2[e[2]+1], e[1], e[2]+1))

        return ans
