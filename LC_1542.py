class Solution:
    def longestAwesome(self, s: str) -> int:
        dp = [len(s)] * 1024
        dp[0] = -1

        cnt = 0
        ans = 0

        for i in range(len(s)):
            num = int(s[i])
            cnt = cnt ^ (1 << num)
            ans = max(ans, i - dp[cnt])

            for j in range(10):
                ans = max(ans, i - dp[cnt ^ (1 << j)])

            dp[cnt] = min(dp[cnt], i)

        return ans
