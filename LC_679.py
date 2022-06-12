class Solution:
    def judgePoint24(self, cards: List[int]) -> bool:
        if len(cards) == 1:
            return math.isclose(cards[0], 24)
        
        for _ in range(len(cards)):
            a = cards.pop(0)
            for _ in range(len(cards)):
                b = cards.pop(0)
                for value in [a + b, a - b, a * b, b and a / b]:
                    cards.append(value)
                    if self.judgePoint24(cards):
                        return True
                    cards.pop()
                cards.append(b)
            cards.append(a)
        return False
