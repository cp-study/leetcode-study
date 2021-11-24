from typing import List


class FenwickTree:
    def __init__(self, n: int):
        self.n = n
        self.tree = [0] * (n + 1)

    def lowbit(self, x: int) -> int:
        return x & -x

    def add(self, x: int, val: int) -> None:
        while x <= self.n:
            self.tree[x] += val
            x += self.lowbit(x)

    def sum(self, x: int) -> int:
        res = 0
        while x > 0:
            res += self.tree[x]
            x -= self.lowbit(x)
        return res


class Solution:
    def countSmaller(self, nums: List[int]) -> List[int]:
        fenwick = FenwickTree(20002)
        res = []
        for i in range(len(nums) - 1, -1, -1):
            res.append(fenwick.sum(nums[i] - 1 + 10001))
            fenwick.add(nums[i] + 10001, 1)

        return res[::-1]
