class Solution:
    def shortestPalindrome(self, s: str) -> str:

        def kmp(s):
            i, j = 1, 0
            n = [0] * len(s)
            while i < len(s):
                if s[i] == s[j]:
                    n[i] = j + 1
                    i += 1
                    j += 1
                elif j > 0:
                    j = n[j - 1]
                else:
                    n[i] = 0
                    i += 1
            return n

        next_arr = kmp(s + "#" + s[::-1])
        return s[next_arr[-1]:][::-1] + s
