import math

class Solution:
    def valueAfterKSeconds(self, n: int, k: int) -> int:
        MOD = 1000_000_007

        nums = [1]*n
        # nums[0] = 0
        # while k:
        #     for i in range(1, n+1):
        #         nums[i] += nums[i-1]
        #         nums[i] %= MOD
        #     k -= 1
        # return nums[n]


        return math.comb(n+k-1, k) % MOD
