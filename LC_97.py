from functools import cache


class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        if len(s1) + len(s2) != len(s3):
            return False

        @cache
        def solve(s1Index: int, s2Index: int) -> bool:
            if s1Index + s2Index >= len(s3):
                return True

            res = False
            s3Index = s1Index + s2Index
            if s1Index < len(s1) and s1[s1Index] == s3[s3Index]:
                res |= solve(s1Index + 1, s2Index)
            if s2Index < len(s2) and s2[s2Index] == s3[s3Index]:
                res |= solve(s1Index, s2Index + 1)
            return res

        return solve(0, 0) or solve(0, 0)
