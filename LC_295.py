from queue import PriorityQueue


class MedianFinder:

    def __init__(self):
        self.leftPriorityQueue = PriorityQueue()
        self.rightPriorityQueue = PriorityQueue()

        self.leftPriorityQueue.put(1000000)
        self.rightPriorityQueue.put(1000000)

    def addNum(self, num: int) -> None:
        left = -self.leftPriorityQueue.get()
        right = self.rightPriorityQueue.get()
        self.leftPriorityQueue.put(-left)
        self.rightPriorityQueue.put(right)

        if num < left:
            self.leftPriorityQueue.put(-num)
        else:
            self.rightPriorityQueue.put(num)

        if self.leftPriorityQueue.qsize() > self.rightPriorityQueue.qsize() + 1:
            val = -self.leftPriorityQueue.get()
            self.rightPriorityQueue.put(val)
        elif self.leftPriorityQueue.qsize() < self.rightPriorityQueue.qsize():
            val = self.rightPriorityQueue.get()
            self.leftPriorityQueue.put(-val)

    def findMedian(self) -> float:
        left = -self.leftPriorityQueue.get()
        right = self.rightPriorityQueue.get()
        self.leftPriorityQueue.put(-left)
        self.rightPriorityQueue.put(right)

        if self.leftPriorityQueue.qsize() == self.rightPriorityQueue.qsize():
            return (left + right) / 2
        elif self.leftPriorityQueue.qsize() > self.rightPriorityQueue.qsize():
            return left
        else:
            return right

# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
