from typing import List


class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        def split(target: int) -> (int, int):
            max_sum = 0
            chunk = 0
            cnt = 1

            for num in nums:
                if chunk + num > target:
                    max_sum = max(max_sum, chunk)
                    chunk = num
                    cnt += 1
                else:
                    chunk += num

            max_sum = max(max_sum, chunk)
            return max_sum, cnt

        ans = 1e9
        left = 0
        right = 1e9

        while left <= right:
            mid = (left + right) // 2
            s, groups = split(mid)

            if groups <= m:
                right = mid - 1
                ans = min(ans, s)
            elif groups > m:
                left = mid + 1

        return ans
