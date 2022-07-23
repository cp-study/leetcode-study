from functools import lru_cache
from typing import List


class Solution:
    def shortestSuperstring(self, words: List[str]) -> str:

        n = len(words)

        # abc, bcd => d
        @lru_cache(None)
        def suffix(w1, w2):
            for i in range(len(words[w1]), -1, -1):
                if words[w1][-i:] == words[w2][:i]:
                    return words[w2][i:]
            return words[w2]

        @lru_cache(None)
        def solve(visited, last):
            # all visited
            if visited == (1 << n) - 1:
                return ""

            ret = ""

            for i in range(n):
                if visited & (1 << i) == 0:
                    res = suffix(last, i) + solve(visited | (1 << i), i)
                    if len(ret) == 0 or len(res) < len(ret):
                        ret = res

            return ret

        words += [""]
        return solve(0, -1)
