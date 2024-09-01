class Solution:
    def kthFactor(self, n: int, k: int) -> int:
        smallfactors = []
        largefactors = []
        i = 1
        while i*i <= n:            
            if n % i == 0:
                smallfactors.append(i)
                if i != n//i:
                    largefactors.append(n//i)
            i += 1
        
        sl = len(smallfactors)
        ll = len(largefactors)
        if k > sl + ll:
            return -1
        if k > sl:
            return largefactors[-(k - sl)]
        return smallfactors[k - 1]
