class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s_length = len(s)
        p_length = len(p)

        # Initialize dp
        dp = [[False] * (p_length+1) for _ in range(s_length + 1)]

        # Initialize dp[0][0], indicating that empty pattern matches empty string
        dp[0][0] = True

        # Initialize dp[0][j], non null pattern matches empty string
        for j in range(1, p_length+1):
            if p[j-1] != "*":
                break
            dp[0][j] = True
        
        for i in range(1, s_length+1):
            for j in range(1, p_length+1):
                # Into the transfer equation
                if p[j-1] == s[i-1] or p[j-1] == "?":
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == "*":
                    dp[i][j] = dp[i-1][j] or dp[i][j-1]
                print(dp)
        
        return dp[s_length][p_length]
