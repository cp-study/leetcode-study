from functools import cache
from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:

        @cache
        def solve(idx: int):
            if idx == len(s):
                return True
            if idx > len(s):
                return False

            ret = False
            for word in wordDict:
                if s[idx:idx + len(word)] == word:
                    ret |= solve(idx + len(word))

            return ret

        return solve(0)
