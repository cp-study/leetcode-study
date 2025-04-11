from typing import List
from collections import Counter


class Solution:
    def visibleMountains(self, peaks: List[List[int]]) -> int:
        intervals = [(x - y, x + y) for x, y in peaks]
        freq = Counter(intervals)

        intervals.sort(key=lambda x: (x[0], -x[1]))

        visible = 0
        max_right = -1

        for left, right in intervals:
            if right > max_right:
                if freq[(left, right)] == 1:
                    visible += 1
                max_right = right

        return visible
