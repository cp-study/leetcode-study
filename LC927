class Solution {
    fun threeEqualParts(arr: IntArray): IntArray {
        //x -> y
        val nope = intArrayOf(-1, -1)
        val indexList = arr.getIndexesOf(1)
        val cnt = indexList.size
        //1이 없는 경우
        if(cnt==0)
            return intArrayOf(0,arr.size-1)
        //포함한 1의 갯수가 3의 배수인지 확인..
        if (cnt % 3 != 0)
            return nope

        val left = Pair(indexList[0],indexList[cnt/3-1])
        val mid = Pair(indexList[cnt/3],indexList[cnt/3*2 -1])
        val right = Pair(indexList[cnt/3*2],indexList[indexList.size-1])

        val str = arr.joinToString("")

        val leftBin = str.substring(left.first,left.second+1)
        val midBin = str.substring(mid.first,mid.second+1)
        val rightBin = str.substring(right.first,right.second+1)

        if(leftBin!=midBin || midBin!=rightBin)
            return nope

        val l1 = mid.first - left.second - 1
        val l2 = right.first - mid.second - 1
        val l3 = arr.size - right.second - 1

        if(l3 > l2 || l3 > l1)
            return nope

        return intArrayOf(left.second + l3, mid.second + l3 + 1)
    }

    private infix fun IntArray.getIndexesOf(value: Int): MutableList<Int> {
        val res = mutableListOf<Int>()
        this.mapIndexed { idx, i ->
            if (i == value)
                res.add(idx)
        }
        return res
    }
}
