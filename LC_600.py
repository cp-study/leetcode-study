from functools import lru_cache


class Solution:
    def findIntegers(self, n: int) -> int:

        @lru_cache(None)
        def solve(digits, end):
            if digits == 0:
                return 1

            if end == 0:
                return solve(digits - 1, 0) + solve(digits - 1, 1)
            else:
                return solve(digits - 1, 0)

        result = 0
        binary = bin(n)[2:]
        prev = '0'

        for i in range(len(binary)):
            if binary[i] == '1':
                result += solve(len(binary) - i - 1, 0)

                # 11
                if prev == '1':
                    return result
            prev = binary[i]

        return result + 1
