from functools import lru_cache
class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        @lru_cache(maxsize=None)
        def dp(i):
            res = []
            for word in wordDict:
                if word != s[i:i+len(word)]:
                    continue
                elif len(word) == len(s)-i:
                    res.append(word)
                else:
                    for sentence in dp(i+len(word)):
                        res.append(word + ' ' + sentence)        
            return res
        
        return dp(0)
