class Solution:
    def simplifyPath(self, path: str) -> str:
        stack = []
        split = path.split('/')
        for i in range(len(split)):
            s = split[i]

            if s == '.':
                continue
            elif s == '..':
                if len(stack) >= 1:
                    stack.pop()
            elif s != '':
                stack.append(s)

        return '/' + '/'.join(stack)
