package leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class SegmentTree {
    long st[];

    SegmentTree(int arr[], int n) {
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new long[max_size];

        constructSTUtil(arr, 0, n - 1, 0);
    }

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    long getSumUtil(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && qe >= se)
            return st[si];

        if (se < qs || ss > qe)
            return 0;

        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        if (i < ss || i > se)
            return;

        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    void updateValue(int arr[], int n, int i, int new_val) {
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        int diff = new_val - arr[i];

        arr[i] = new_val;

        updateValueUtil(0, n - 1, i, diff, 0);
    }

    long getSum(int n, int qs, int qe) {
        if (qs < 0 || qe > n - 1 || qs > qe) {
            return 0;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    long constructSTUtil(int arr[], int ss, int se, int si) {
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }
}

class Segment {
    long val;
    int l, r;
    public Segment(long val, int l, int r) {
        this.val = val;
        this.l = l;
        this.r = r;
    }
}

class Solution {
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums, n);

        long[] ans = new long[removeQueries.length];

        TreeMap<Integer, Segment> tm = new TreeMap<>();
        tm.put(0, new Segment(tree.getSum(n, 0, n-1),0,n-1));
        PriorityQueue<Segment> pq = new PriorityQueue<>((t1,t2)-> {
            if(t2.val - t1.val<0) {
                return -1;
            } else if(t2.val-t1.val>0) {
                return 1;
            } else return 0;
        });
        HashSet<Segment> hs = new HashSet<>();
        for(int i=0;i<removeQueries.length;i++) {
            int idx = removeQueries[i];

            Map.Entry<Integer,Segment> entry = tm.floorEntry(idx);
            Segment seg = entry.getValue();
            tm.remove(entry.getKey());
            hs.remove(entry.getValue());
            tree.updateValue(nums, n, idx, 0);

            if(seg.l <= idx-1) {
                Segment left = new Segment(tree.getSum(n, seg.l, idx-1), seg.l, idx-1);
                tm.put(seg.l, left);
                hs.add(left);
                pq.add(left);
            }
            if(idx+1 <= seg.r) {
                Segment right = new Segment(tree.getSum(n, idx+1, seg.r),idx+1, seg.r);
                tm.put(idx+1, right);
                hs.add(right);
                pq.add(right);
            }
           while(!pq.isEmpty()) {
               if(!hs.contains(pq.peek())) {
                   pq.poll();
               } else break;
           }
           if(!pq.isEmpty()) {
               ans[i] = pq.peek().val;
           }
        }
        return ans;
    }
}