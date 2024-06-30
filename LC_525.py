class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        indices = {}

        res = 0
        cnt = 0
        indices[0] = -1

        for i, num in enumerate(nums):
            if num == 1:
                cnt += 1
            else:
                cnt -= 1

            if cnt in indices:
                res = max(res, i - indices[cnt])
            else:
                indices[cnt] = i
        return res
