class BIT:
    def __init__(self, n):
        self.sums = [0] * (n+1)
    
    def update(self, i, delta):
        while i < len(self.sums):
            self.sums[i] += delta
            i += i & (-i)
    
    def query(self, i):
        res = 0
        while i > 0:
            res += self.sums[i]
            i -= i & (-i)
        return res

class Solution:
    def goodTriplets(self, A, B):
        d = {x: i for i, x in enumerate(A)}
        n = len(A)
        arr = [d[B[i]] for i in range(n)]

        BIT1, BIT2, ans = BIT(n), BIT(n), 0
        for i in arr:
            ans += BIT2.query(i)
            BIT1.update(i + 1, 1)
            less = BIT1.query(i)
            BIT2.update(i + 1, less)
        return ans
