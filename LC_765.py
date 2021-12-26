from typing import List


class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        pos = [0] * len(row)

        for i in range(len(row)):
            pos[row[i]] = i

        ans = 0
        for i in range(0, len(row), 2):
            partner = row[i] % 2 == 0 and row[i] + 1 or row[i] - 1

            if pos[partner] != i + 1:
                ans += 1
                row[i + 1], row[pos[partner]] = row[pos[partner]], row[i + 1]
                pos[row[pos[partner]]] = pos[partner]

        return ans
