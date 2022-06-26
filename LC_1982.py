from collections import Counter
from typing import List


class Solution:
    def recoverArray(self, n: int, sums: List[int]) -> List[int]:
        result = []
        sums.sort()

        while len(sums) > 1:
            num = sums[-1] - sums[-2]
            count_map = Counter(sums)  # Get count of each element
            excluding = []  # Subset sums that do NOT contain num
            including = []  # Subset sums that contain num

            for x in sums:
                if count_map.get(x) > 0:
                    excluding.append(x)
                    including.append(x + num)
                    count_map[x] -= 1
                    count_map[x + num] -= 1

            # Check validity of excluding set
            if 0 in excluding:
                sums = excluding
                result.append(num)
            else:
                sums = including
                result.append(-1 * num)

        return result
