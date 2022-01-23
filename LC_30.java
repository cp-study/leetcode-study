package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_30 {

    private int unit;

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        unit = words[0].length();
        for (String w : words) {
            wordMap.merge(w, 1, (k, v) -> wordMap.get(w) + 1);
        }
        List<Integer> output = new ArrayList<>();
        int size = words.length * words[0].length();
        for (int i = 0; i <= s.length() - size; i++) {
            String subStr = s.substring(i, i + size);
            Map<String, Integer> tmpMap = new HashMap<>(wordMap);
            if (check(tmpMap, subStr)) {
                output.add(i);
            }
        }
        return output;
    }

    private boolean check(Map<String, Integer> tmpMap, String subStr) {
        int cursor = 0;
        while (cursor < subStr.length()) {
            String curWord = subStr.substring(cursor, cursor + unit);
            int val = tmpMap.getOrDefault(curWord, 0);
            if (val > 0) {
                tmpMap.compute(curWord, (k, v) -> v - 1);
                cursor += unit;
            } else {
                return false;
            }
        }
        return true;
    }

}
