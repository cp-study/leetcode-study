class Solution:
    def minimumBoxes(self, n: int) -> int:
        cur = i = j = 0
        while cur < n:
            j += 1
            i += j
            cur += i
        if cur == n: return i
        
        cur -= i
        i -= j
        j = 0
        while cur < n:
            j += 1
            cur += j
        return i+j
        
