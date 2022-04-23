from functools import lru_cache
from math import comb
from typing import List


class Solution:
    def waysToFillArray(self, queries: List[List[int]]) -> List[int]:
        primes = []
        for i in range(2, 100):
            is_prime = True
            for j in range(2, i):
                if i % j == 0:
                    is_prime = False
                    break
            if is_prime:
                primes.append(i)

        def count(nn, kk):
            ret = 1
            for p in primes:
                cnt = 0
                while kk % p == 0:
                    kk //= p
                    cnt += 1
                ret *= comb(nn + cnt - 1, cnt)
            if kk > 1:
                ret *= comb(nn, 1)
            return ret % (10 ** 9 + 7)

        res = []
        for n, k in queries:
            res.append(count(n, k))
        return res
