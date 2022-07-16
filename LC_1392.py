class Solution:
    def longestPrefix(self, s: str) -> str:
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

        return s[:kmp(s)[-1]]
