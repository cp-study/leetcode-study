import java.util.*

class LC_297 {

}
class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val tmp = mutableListOf<Any>()
        val stack = LinkedList<TreeNode>()
        stack.push(root)
        while (!stack.isEmpty()){
            val cur:TreeNode = stack.pop()
            tmp.add(cur.value)
            if(cur.left !=null){
                stack.push(cur.left)
            }
            if(cur.right !=null){
                stack.push(cur.right)
            }
        }
        return tmp.joinToString(separator = ",")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        if(data.length==2)
            return null
        val target = data.slice(1..data.length-2)
        val splitted = target.split(",")
            .map {
                if (it != "null") {
                    TreeNode(it.toInt())
                } else {
                    null
                }
            }
        if(splitted.isEmpty()){
            return null
        }
        val root = splitted[0]
        if(splitted.size==1)
            return root
        for (idx in 1..splitted.size){
            val parentIdx = (idx+1)/2-1
            if(even(idx+1)){
                splitted[parentIdx]?.left = splitted[idx]
            }else{
                splitted[parentIdx]?.right = splitted[idx]
            }
        }
        return root
    }
    private fun even(num: Int) = num.rem(2) == 0
}

data class TreeNode(var value: Int){
    var left: TreeNode? = null
    var right: TreeNode? = null

    constructor(`val`: Int, left:TreeNode?, right:TreeNode?) : this(`val`)
}

fun main() {
    val instance = Codec()
    //val test = instance.deserialize("[]")
    val test2 = instance.deserialize("[1,2,3,null,null,4,5]")
    print("end")
}
