package com

import java.util.*

class Solution {
    fun eatenApples(apples: IntArray, days: IntArray): Int {
        var day = 1
        var ans = 0
        val pq = PriorityQueue<Apple>()
        pq.offer(Apple(quantity = apples[day - 1], rotDay = day + days[day - 1]))
        while(true) {
            if(pq.isEmpty() && day >= apples.size) break
            var top = pq.poll()
            while (top != null && top.canEat(day).not() && pq.isNotEmpty()) {
                top = pq.poll()
            }
            // 현재 먹을 수 있는게 있으면 먹음
            if(top!= null && top.canEat(day)) {
                pq.offer(top.eat())
                ans += 1
            }
            day += 1
            if (day <= apples.size)
                pq.offer(Apple(quantity = apples[day - 1], rotDay = day + days[day - 1]))
        }
        return ans
    }

    class Apple(
        var quantity: Int,
        private val rotDay: Int
    ) : Comparable<Apple> {
        override fun compareTo(other: Apple): Int {
            if (this.rotDay > other.rotDay) return 1
            return -1
        }

        fun canEat(currentDay: Int): Boolean = this.quantity > 0 && currentDay < rotDay


        fun eat(): Apple {
            if (this.quantity > 0) {
                this.quantity = this.quantity - 1
                return this
            }
            return this
        }
    }
}

fun main() {
    val sol = Solution()
    val apples = intArrayOf(1,2,3,5,2)
    val days = intArrayOf(3,2,1,4,2)
    sol.eatenApples(apples, days)
}