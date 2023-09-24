class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        glass = [[0] * 101 for i in range(101)]
        glass[0][0] = poured

        for i in range(query_row + 1):
            for j in range(i + 1):
                if glass[i][j] >= 1:
                    glass[i + 1][j] += (glass[i][j] - 1) * 0.5
                    glass[i + 1][j + 1] += (glass[i][j] - 1) * 0.5

                glass[i][j] = min(1, glass[i][j])

        return glass[query_row][query_glass]
