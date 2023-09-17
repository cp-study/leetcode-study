package leetcode;

import java.util.HashMap;
import java.util.Map;

class LC_2840 {
    public boolean checkStrings(String s1, String s2) {

        Map<Character, Integer> oddSt1 = new HashMap<>();
        Map<Character, Integer> oddSt2 = new HashMap<>();
        Map<Character, Integer> evenSt1 = new HashMap<>();
        Map<Character, Integer> evenSt2 = new HashMap<>();

        for(int i=0;i<s1.length();i++) {
            if(i % 2 == 0) {
                evenSt1.put(s1.charAt(i), evenSt1.getOrDefault(s1.charAt(i),0)+1);
                evenSt2.put(s2.charAt(i), evenSt2.getOrDefault(s2.charAt(i),0)+1);
            } else {
                oddSt1.put(s1.charAt(i), oddSt1.getOrDefault(s1.charAt(i),0)+1);
                oddSt2.put(s2.charAt(i), oddSt2.getOrDefault(s2.charAt(i),0)+1);
            }
        }
        return evenSt1.equals(evenSt2) && oddSt1.equals(oddSt2);
    }
}