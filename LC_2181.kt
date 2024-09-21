/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class LC_2181 {
    fun mergeNodes(head: ListNode?): ListNode? {
        var res: ListNode? = null
        var tail: ListNode? = null
        var curNode: ListNode? = head
        var tmp = 0
        while (true) {
            if (curNode == null) break
            when (val v = curNode.`val`) {
                0 -> {
                    if (tmp != 0) {
                        val newNode = ListNode(tmp)
                        if (res == null) res = newNode
                        if (tail == null) tail = newNode
                        else {
                            tail.next = newNode
                            tail = newNode
                        }
                        tmp = 0
                    }
                }
                else -> tmp += v
            }
            curNode = curNode?.next
        }
        return res
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun main() {
    val sol = LC_2181()
    val head = ListNode(0)
    var tail = head
    listOf(3,1,0,4,5,2,0).forEach {
        val tmp = ListNode(it)
        tail.next = tmp
        tail = tmp
    }
    sol.mergeNodes(head)
}