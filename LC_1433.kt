package com

class Solution {
    fun checkIfCanBreak(s1: String, s2: String): Boolean {
        val arr1 = s1.toMutableList()
        val arr2 = s2.toMutableList()

        arr1.sort()
        arr2.sort()

        var bool: Boolean? = null

        for (i in arr1.indices) {
            if(bool == null) {
                when {
                    arr1[i] > arr2[i] -> bool = true;
                    arr1[i] < arr2[i] -> bool = false;
                    arr1[i] == arr2[i] -> continue
                }
            } else {
                when {
                    arr1[i] > arr2[i] -> {
                        if(bool.not()) return false
                    }
                    arr1[i] < arr2[i] -> {
                        if(bool) return false
                    }
                    arr1[i] == arr2[i] -> continue
                }
            }
        }

        return true
    }
}

