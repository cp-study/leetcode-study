class Solution {
    fun distinctEchoSubstrings(text: String): Int {
        val ansSet = mutableSetOf<String>()

        for (i in text.indices){
            for (j in i+2..text.length step 2){
                val mid = (i + j)/2
                val l = text.substring(i,mid)
                val r = text.substring(mid,j)
                if(l==r && !ansSet.contains(text.substring(i,j))){
                    ansSet.add(text.substring(i,j))
                }
            }
        }
        return ansSet.size
    }
}
