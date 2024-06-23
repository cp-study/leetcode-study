from collections import defaultdict
from typing import List


class Solution:
    def countCompleteDayPairs(self, hours: List[int]) -> int:
        mod_cnt = defaultdict(int)

        ans = 0
        start = len(hours) - 2
        i = len(hours) - 1
        while i >= 0:
            h = hours[i]
            if i <= start:
                ans += mod_cnt[(24 - (h % 24)) % 24]
            mod_cnt[h % 24] += 1
            i -= 1

        return ans
