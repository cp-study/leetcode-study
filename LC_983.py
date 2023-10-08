from functools import cache
from typing import List


class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        @cache
        def solve(index: int):
            if index >= len(days):
                return 0

            # 1-day
            ret = solve(index + 1) + costs[0]

            # 7-day
            nextIndex = index + 1
            while nextIndex < len(days) and days[nextIndex] < days[index] + 7:
                nextIndex += 1

            ret = min(ret, solve(nextIndex) + costs[1])

            # 30-day
            while nextIndex < len(days) and days[nextIndex] < days[index] + 30:
                nextIndex += 1

            ret = min(ret, solve(nextIndex) + costs[2])
            return ret

        return solve(0)
