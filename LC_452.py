class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        points = sorted(points)

        cnt = 0
        minRight = -(1<<32)
        for point in points:
            [left, right] = point

            if left > minRight:
                cnt += 1
                minRight = right
            else:
                minRight = min(minRight, right)

        return cnt
