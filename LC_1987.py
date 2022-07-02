class Solution:
    def numberOfUniqueGoodSubsequences(self, binary: str) -> int:
        end0 = 0
        end1 = 0

        for i in range(len(binary)):
            if binary[i] == '0':
                end0 = (end0 + end1) % 1000000007
            else:
                end1 = (end0 + end1 + 1) % 1000000007

        if '0' in binary:
            return (end0 + end1 + 1) % 1000000007
        return (end0 + end1) % 1000000007
