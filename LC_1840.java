package leetcode;

import java.util.Arrays;

class LC_1840 {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int len = restrictions.length + 1;
        int[][] arr = new int[len+1][2];
        arr[0][0] = 1;
        arr[0][1] = 0;
        arr[len][0] = n;
        arr[len][1] = n-1;
        for(int i = 1; i<len; ++i){
            arr[i] = restrictions[i-1];
        }

        // Left to right
        for(int i = 0; i<len; ++i){
            arr[i+1][1] = Math.min(arr[i+1][1], arr[i][1] + (arr[i+1][0] - arr[i][0]));
        }

        // Right to left
        for(int i = len; i>0; --i){
            arr[i-1][1] = Math.min(arr[i-1][1], arr[i][1] + (arr[i][0] - arr[i-1][0]));
        }

        int max = 0;

        for(int i = 0; i<len; ++i){
            int leftPos = arr[i][0], leftH = arr[i][1],
                    rightPos = arr[i+1][0], rightH = arr[i+1][1];

            int diff = Math.max(leftH, rightH) - Math.min(leftH, rightH);
            leftPos += diff; // Move leftPos until height same as right building

            max = Math.max(max, Math.max(leftH, rightH) + (rightPos - leftPos)/2);
        }

        return max;
    }
}