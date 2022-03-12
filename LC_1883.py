from functools import lru_cache
from typing import List


class Solution:
    def minSkips(self, dist: List[int], speed: int, hoursBefore: int) -> int:

        def ceil(d):
            return (d // speed if d % speed == 0 else d // speed + 1) * speed

        # idx 까지 num_skips 스킵을 써서 이동한 거리
        @lru_cache(maxsize=None)
        def solve(idx, num_skips):
            if num_skips < 0:
                return 987654321

            if idx >= len(dist):
                return 0

            # rest
            ret = ceil(solve(idx + 1, num_skips) + dist[idx])

            # skip
            ret = min(ret, solve(idx + 1, num_skips - 1) + dist[idx])

            return ret

        for i in range(len(dist)):
            ans = solve(0, i)
            if ceil(ans) <= hoursBefore * speed:
                return i

        return -1
