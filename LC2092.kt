class Solution {
   fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
        val secret = mutableSetOf(0, firstPerson)
        val noSecret = mutableSetOf<Int>()

        val uf = DisjointSetImpl(n)
        uf.union(0,firstPerson)

        val meetingMap = mutableMapOf<Int,Set<Pair<Int,Int>>>()

        meetings.forEach { (x,y,time) ->
            meetingMap.merge(time, mutableSetOf(Pair(x,y))) {
                    v1,_ -> v1.plus(Pair(x,y))
            }
        }

        val keySet = meetingMap.keys.toSortedSet()
        keySet.forEach { time->
            val curSet = mutableSetOf<Int>()
            
            meetingMap[time]!!.forEach { (x,y)->
                uf.union(x,y)
                curSet.add(x)
                curSet.add(y)
            }
            curSet.forEach { 
                if(uf.find(it) == uf.find(0)){
                    secret.add(it)
                } else {
                    noSecret.add(it)
                }
            }
        }

        return secret.toList()
    }
}

class DisjointSetImpl(size: Int) : DisjointSet {

    private var arr: IntArray

    init {
        arr = IntArray(size + 1) { it }
    }

    override fun union(a: Int, b: Int) {
        val ap = find(a)
        val bp = find(b)
        arr[bp] = ap
    }

    override fun find(target: Int): Int {
        val parent = arr[target]
        return if (target == parent) {
            target
        } else {
            arr[target] = find(parent)
            arr[target]
        }
    }
}

interface DisjointSet {
    fun union(a: Int, b: Int)
    fun find(target: Int): Int
}
