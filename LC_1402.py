class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        A = satisfaction
        res = total = 0
        A.sort()
        while A and A[-1] + total > 0:
            total += A.pop()
            res += total
        return res
