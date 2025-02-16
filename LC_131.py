class Solution:
    def partition(self, s: str) -> List[List[str]]:
        result = []

        @cache
        def is_palindrome(x, y):            
            for i in range(y-x):
                if s[x+i] != s[y-i-1]:
                    return False
            return True
        
        @cache
        def check(idx):
            if idx == len(s):
                return [[]]
            
            ret = []
            for i in range(idx, len(s)):
                substr = s[idx:i+1]

                if is_palindrome(idx, i + 1):
                    palindrome = check(i+1)
                    
                    for p in palindrome:
                        ret.append([substr] + p)
            
            return ret
        
        return check(0)
