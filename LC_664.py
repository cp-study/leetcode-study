class Solution:
    def strangePrinter(self, s: str) -> int:
        s = re.sub(r'(.)\1*', r'\1', s)
        
        if not s: return 0
        if len(s) < 2: return 1
        n = len(s)
        
        # Bottom-up DP: dp[i][j] is the # of steps needed to print s[i:j+1]
        dp = [[0]*n for _ in range(n)]
        for i in range(n):
            dp[i][i] = 1
        
        for d in range(1, n):
            for l in range(n):
                r = l + d
                if r >= n: continue
                # We can view each substring as a circular string
                if s[l] == s[r]:
                    dp[l][r] = min(dp[l][k]+dp[k+1][r] for k in range(l, r)) - 1
                else:
                    dp[l][r] = min(dp[l][k]+dp[k+1][r] for k in range(l, r))
        return dp[0][n-1]
