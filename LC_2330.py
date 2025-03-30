class Solution:
    def makePalindrome(self, s: str) -> bool:
        n = len(s)

        cnt = 0
        for i in range(len(s)//2):
            if s[i] != s[n-i-1]:
                cnt += 1
            if cnt > 2:
                return False
        return True