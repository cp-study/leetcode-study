from typing import List


class Solution:
    def fallingSquares(self, positions: List[List[int]]) -> List[int]:
        results = []
        heights = {}
        max_height = 0

        for left, side_length in positions:
            start = left
            end = left + side_length - 1

            nearby = [key for key in heights.keys() if (start <= key[1] and key[0] <= end)]

            if len(nearby) > 0:
                h = max(heights[key] for key in nearby) + side_length
            else:
                h = side_length

            heights[(start, end)] = h

            max_height = max(max_height, h)
            results.append(max_height)

        return results
