class Solution:
    def numOfWays(self, n: int) -> int:
        dp = [[0] * 12 for i in range(n + 1)]

        color = [[0, 1, 0],
                 [0, 1, 2],
                 [0, 2, 0],
                 [0, 2, 1],
                 [1, 0, 1],
                 [1, 0, 2],
                 [1, 2, 0],
                 [1, 2, 1],
                 [2, 0, 1],
                 [2, 0, 2],
                 [2, 1, 0],
                 [2, 1, 2]]

        for i in range(1, n + 1):
            for j in range(len(color)):
                colors = [color[j][0], color[j][1], color[j][2]]

                if i == 1:
                    dp[i][j] = 1
                    continue

                for k in range(len(color)):
                    prev_colors = [color[k][0], color[k][1], color[k][2]]

                    if colors[0] == prev_colors[0] or colors[1] == prev_colors[1] or colors[2] == prev_colors[2]:
                        continue

                    dp[i][j] += dp[i - 1][k]

        return sum(dp[n]) % (10 ** 9 + 7)
