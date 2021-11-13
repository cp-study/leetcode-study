from collections import deque

class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        ans = []
        dq = deque()
        for i in range(len(nums)):
            if dq and i-dq[0] == k:
                dq.popleft()
            while dq:
                if nums[dq[-1]] < nums[i]:
                    dq.pop()
                else:
                    break
            dq.append(i)
            if i >= k-1:
                ans.append(nums[dq[0]])
        return ans
