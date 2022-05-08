class Solution:
    def appealSum(self, s: str) -> int:
        lastSeenMap = {s[0]: 0}
        prev, curr, res = 1, 0, 1
        
        for i in range(1, len(s)):
            if s[i] in lastSeenMap:
                curr = prev + (i - lastSeenMap[s[i]])
            else:
                curr = prev + (i + 1)
            res += curr
            prev = curr
            lastSeenMap[s[i]] = i
        return res
