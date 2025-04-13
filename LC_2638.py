from typing import List
from functools import cache


class Solution:
    def countTheNumOfKFreeSubsets(self, nums: List[int], k: int) -> int:
        n = len(nums)

        nums.sort()
        st = set(nums)
        lsts = []
        visited = set()
        for i in range(n):
            if nums[i] not in visited:
                lst = []
                j = 1
                next = nums[i]
                while next in st:
                    lst.append(next)
                    visited.add(next)
                    next = nums[i] + j*k
                    j += 1
                lsts.append(lst)

        @cache
        def get_cnt(idx, size) -> int:
            if idx >= size:
                return 0
            ret = 0

            ret += get_cnt(idx+2,size) + 1
            ret += get_cnt(idx+1,size)

            return ret

        ans = 1
        for lst in lsts:
            ans *= (get_cnt(0, len(lst)) + 1)

        return ans
