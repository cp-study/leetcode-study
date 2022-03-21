import collections


class FreqStack:

    def __init__(self):
        self.count = collections.defaultdict(int)
        self.stack = [collections.deque() for _ in range(20000)]
        self.max_freq = 0

    def push(self, val: int) -> None:
        count = self.count[val] if val in self.count else 0
        self.count[val] = count + 1

        if count + 1 > self.max_freq:
            self.max_freq = count + 1

        self.stack[count + 1].append(val)

    def pop(self) -> int:
        val = self.stack[self.max_freq].pop()
        self.count[val] -= 1
        if not self.stack[self.max_freq]:
            self.max_freq -= 1
        return val

# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()
