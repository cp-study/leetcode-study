package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class LC_1815 {
    int ans = 0;
    Map<List<Integer>, Integer> dp;
    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] cntArr = new int[batchSize];
        dp = new HashMap<>();

        // Not neccessary, but helps to increase efficiency.
        for(int group : groups) {
            if(group % batchSize == 0) { // When one element happy
                ans++;
            } else if(cntArr[(batchSize-(group%batchSize))] >= 1) { // Two element happy
                ans++;
                cntArr[batchSize-(group%batchSize)]--;
            } else {
                cntArr[group%batchSize]++;
            }
        }
        ans += dfs(Arrays.stream(cntArr).boxed().collect(Collectors.toList()), 0, batchSize);
        return ans;
    }

    int dfs(List<Integer> cntList, int left, int batchSize) {
        if(dp.containsKey(cntList)) {
            return dp.get(cntList);
        }

        int res = 0;
        for(int i=1;i<batchSize;i++) {
            if(cntList.get(i) > 0) {
                cntList.set(i, cntList.get(i)-1);
                res = Math.max(res,(left==0?1:0) + dfs(cntList,(batchSize + left + i) % batchSize, batchSize));
                cntList.set(i, cntList.get(i)+1);
            }
        }
        dp.put(cntList, res);
        return res;
    }
}
