from typing import List
import math


class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        l = 1
        r = max(piles)

        ans = 0
        while l <= r:
            mid = (l+r)//2

            isPossible = False
            hours_need = 0
            for pile in piles:
                hours_need += math.ceil(pile/mid)
            if hours_need <= h:
                isPossible = True

            if isPossible:
                ans = mid
                r = mid - 1
            else:
                l = mid + 1

        return ans
