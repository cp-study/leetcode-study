from functools import lru_cache


class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:

        @lru_cache(None)
        def solve(idx: int):
            if idx == len(s):
                return 1
            if s[idx] == '0':
                return 0

            ret = 0
            for i in range(idx, len(s)):
                value = int(s[idx:i + 1])

                if value > k:
                    break

                ret += solve(i + 1)

            return ret % (10 ** 9 + 7)

        return solve(0)
