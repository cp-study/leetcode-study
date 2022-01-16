from functools import lru_cache


class Solution:
    def findGoodStrings(self, n: int, s1: str, s2: str, evil: str) -> int:

        mod = 10 ** 9 + 7

        def get_kmp_next(s):
            next_arr = [0] * len(s)
            i, j = 1, 0

            while i < len(s):
                while j > 0 and s[i] != s[j]:
                    j = next_arr[j - 1]

                if s[i] == s[j]:
                    j += 1

                next_arr[i] = j
                i += 1
            return next_arr

        kmp_next = get_kmp_next(evil)

        @lru_cache(None)
        def solve(idx, is_prefix_s1, is_prefix_s2, prefix_evil):
            if prefix_evil == len(evil):
                return 0
            if idx == n:
                return 1

            total = 0
            first = ord(s1[idx]) if is_prefix_s1 else ord('a')
            last = ord(s2[idx]) if is_prefix_s2 else ord('z')

            for c in range(first, last + 1):
                char = chr(c)

                next_prefix_s1 = is_prefix_s1 and c == first
                next_prefix_s2 = is_prefix_s2 and c == last

                next_prefix_evil = prefix_evil
                while next_prefix_evil and evil[next_prefix_evil] != char:
                    next_prefix_evil = kmp_next[next_prefix_evil - 1]

                if char == evil[next_prefix_evil]:
                    next_prefix_evil += 1

                total += solve(idx + 1, next_prefix_s1, next_prefix_s2, next_prefix_evil)
                total %= mod

            return total

        return solve(0, True, True, 0)
