import math
from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        maxim = -math.inf

        def max_sum(node: Optional[TreeNode]) -> int:
            if not node:
                return 0

            left_sum = max(0, max_sum(node.left))
            right_sum = max(0, max_sum(node.right))

            nonlocal maxim
            maxim = max(maxim, node.val + left_sum + right_sum)

            return max(node.val + left_sum, node.val + right_sum)

        max_sum(root)
        return maxim
