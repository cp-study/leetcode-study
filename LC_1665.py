class Solution:
   def minimumEffort(self, A):
        A.sort(key=lambda a: a[1] - a[0])
        res = 0
        for a, m in A:
            res = max(res + a, m)
        return res
