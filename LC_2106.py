import bisect
class Solution:
    def maxTotalFruits(self, fruits: List[List[int]], startPos: int, k: int) -> int:
        pos = list(map(lambda x: x[0], fruits))
        amount = list(map(lambda x: x[1], fruits))
        n = len(pos)
        presum = [0] * (n + 1)
        for i in range(1, n + 1):
            presum[i] += presum[i - 1] + amount[i - 1]
        res = 0
        for i in range(n):
            if abs(pos[i] - startPos) > k:
                continue
            if pos[i] >= startPos:
                dist_left = max(0, k - (pos[i] - startPos) * 2)
                to_left = startPos - dist_left
                found = bisect.bisect_left(pos, to_left)
                res = max(res, presum[i + 1] - presum[found])
            if pos[i] <= startPos:
                dist_right = max(0, k - (startPos - pos[i]) * 2)
                to_right = startPos + dist_right
                found = bisect.bisect(pos, to_right)
                res = max(res, presum[found] - presum[i])
        return res
