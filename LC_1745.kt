package com

class Solution {
    fun checkPartitioning(s: String): Boolean {
        if(s.length<3){
            return false
        }

        val dp = Array(s.length){BooleanArray(s.length)}

        for(i in 1 until s.length-1){
            for(j in i+1 until s.length){
                if(isPalindrom(s, 0, i-1, dp)
                    && isPalindrom(s, i,j-1, dp)
                    && isPalindrom(s, j, s.length-1, dp)){

                    return true
                }
            }
        }


        return false
    }

    fun isPalindrom(s:String, i:Int, j:Int, dp:Array<BooleanArray>):Boolean{
        if(dp[i][j]){
            return true
        }

        if(i<0 || j>=s.length || i>j){
            return false
        }

        var size = j-i+1
        var start = i
        var end = j

        while(start<=end) {
            if(dp[start][end]){
                return true
            }

            if(s[start]!=s[end]){
                return false
            }

            start++
            end--
        }

        start = i
        end = j
        while(start<=end) {
            dp[start][end] =true
            start++
            end--
        }

        return true
    }
}
