class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        res = 0
        check = (1<<31)
        while check > 0:
            if (left & right & check) == check:
                res += check
            elif (left & check) != (right & check):
                break
            check >>= 1

        return res
