class Solution:
    def minMovesToMakePalindrome(self, s: str) -> int:
        s = list(s)

        result = 0
        i = 0
        while i < len(s) // 2:
            target = s[i]

            last = len(s) - i - 1
            j = last
            while j > i:
                if s[j] == target:
                    break
                j -= 1

            # move s[i] to len(s)//2
            if i == j:
                s.pop(i)
                result += len(s) // 2 - j
                continue

            for k in range(j, last):
                s[k], s[k + 1] = s[k + 1], s[k]

            result += last - j
            i += 1

        return result
