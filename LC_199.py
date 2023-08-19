# Definition for a binary tree node.
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        return self.getRightNode(root)

    def getRightNode(self, node: TreeNode) -> List[int]:
        if not node:
            return []

        res = [node.val]

        right = self.getRightNode(node.right)
        left = self.getRightNode(node.left)

        if len(right) < len(left):
            left[0:len(right)] = right
            res.extend(left)
        else:
            res.extend(right)

        return res
