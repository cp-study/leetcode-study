package leetcode;

import java.util.*;
import java.util.stream.Collectors;

class LC_1125 {
    public int[] smallestSufficientTeam(String[] req_skills,
                                        List<List<String>> people) {
        Map<String, Integer> skillIdxMp = new HashMap<>();

        int currNeedBitMp = 0;
        for(int i=0;i<req_skills.length;i++) {
            skillIdxMp.put(req_skills[i], i);
            currNeedBitMp |= 1 << i;
        }

        List<Integer>[][] dp = new ArrayList[people.size()][(int)Math.pow(2,16)+1];

        return solve(0, currNeedBitMp, skillIdxMp, people, dp);
    }

    int[] solve(int idx, int currNeedBitMp,
                Map<String, Integer> skillIdxMp, List<List<String>> people, List<Integer>[][] dp) {
        if(currNeedBitMp == 0) return new int[0];
        if(idx >= people.size()) {
            int[] invalidRet = new int[1];
            invalidRet[0] = -1;
            return invalidRet;
        }

        if(dp[idx][currNeedBitMp] != null) {
            return dp[idx][currNeedBitMp].stream().mapToInt(i->i).toArray();
        }

        int[] ret = solve(idx+1, currNeedBitMp, skillIdxMp, people, dp);
        List<String> skills = people.get(idx);
        int nextNeedBitMp = currNeedBitMp;
        for(String skill : skills) {
            int idxOfSkill = skillIdxMp.get(skill);
            nextNeedBitMp &= ~(1 << idxOfSkill);
        }
        if(currNeedBitMp != nextNeedBitMp) {
            int[] addedRet = solve(idx + 1, nextNeedBitMp, skillIdxMp, people, dp);
            int[] newAddedRet = new int[addedRet.length+1];
            newAddedRet[0] = idx;
            for(int i=0;i<addedRet.length;i++) {
                newAddedRet[i+1] = addedRet[i];
            }
            if(newAddedRet[newAddedRet.length-1] != -1) {
                if(newAddedRet.length < ret.length || (ret.length >= 1 && ret[ret.length-1] == -1))
                    ret = newAddedRet;
            }
        }

        dp[idx][currNeedBitMp] = Arrays.stream(ret).boxed().collect(Collectors.toList());
        return ret;
    }
}