/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class LC_86 {
    fun partition(head: ListNode?, x: Int): ListNode? {
        var leftHead: ListNode? = null
        var leftTail: ListNode? = null
        var rightHead: ListNode? = null
        var rightTail: ListNode? = null

        var cur = head

        while (cur != null) {
            val nextNode = cur.next  // 연결을 변경하기 전에 미리 저장해 둠
            cur.next = null // 현재 노드를 분리시킴

            if (cur.`val` < x) {
                if (leftHead == null) {
                    leftHead = cur
                    leftTail = cur
                } else {
                    leftTail!!.next = cur
                    leftTail = cur
                }
            } else {
                if (rightHead == null) {
                    rightHead = cur
                    rightTail = cur
                } else {
                    rightTail!!.next = cur
                    rightTail = cur
                }
            }
            cur = nextNode
        }

        // 작은 값 리스트의 마지막 노드가 큰 값 리스트의 첫 번째 노드를 가리키도록 연결
        leftTail?.next = rightHead

        // 결과는 작은 값 리스트의 head가 되거나, 모든 값이 큰 경우엔 큰 값 리스트의 head
        return leftHead ?: rightHead
    }
}


fun main(args: Array<String>) {
    val values = listOf(1, 4, 3, 2, 5, 2)

    val head = ListNode(1)
    var cur = head
    values.forEachIndexed { idx, i ->
        if (idx != 0) {
            cur.next = ListNode(i)
            cur = cur.next!!
        }
    }
    val sol = LC_86().partition(head, 3)
}


