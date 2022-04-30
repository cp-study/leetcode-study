package leetcode;

import java.util.Arrays;
import java.util.Comparator;

class LC_2251 {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Person[] personsAccum = new Person[persons.length];
        for(int i=0;i<persons.length;i++) {
            personsAccum[i] = new Person(i, persons[i], 0);
        }
        Arrays.sort(personsAccum, Comparator.comparingInt((Person a) -> a.time));

        for(int i=0;i<flowers.length;i++) {
            int s = flowers[i][0];
            int e = flowers[i][1];

            int l =0;
            int r = personsAccum.length-1;
            int m = 0;

            int leftBound = 0;
            int rightBound = 0;

            while(l<=r) {
                m = (l+r)/2;
                if(personsAccum[m].time >= s) {
                    r = m-1;
                } else if(personsAccum[m].time < s) {
                    l = m+1;
                }
            }
            leftBound = r+1;
            if(leftBound < personsAccum.length) personsAccum[leftBound].accum++;

            l =0;
            r = personsAccum.length-1;
            while(l<=r) {
                m = (l+r)/2;

                if(personsAccum[m].time > e) {
                    r = m-1;
                } else if(personsAccum[m].time <= e) {
                    l = m+1;
                }
            }
            rightBound = l-1;
            if(rightBound+1 < personsAccum.length) personsAccum[rightBound+1].accum--;
            //System.out.println("leftBound: " + leftBound + "  rightBound: " + rightBound);
        }
        for(int i=0; i<personsAccum.length;i++) {
            if(i!=0) {
                personsAccum[i].accum += personsAccum[i-1].accum;
            }
        }
        Arrays.sort(personsAccum, Comparator.comparingInt((Person a) -> a.idx));

        int[] ret = new int[personsAccum.length];
        for(int i=0;i<ret.length;i++) {
            ret[i] = personsAccum[i].accum;
        }
        return ret;
    }

    class Person {
        int idx;
        int time;
        int accum;

        public Person(int idx, int time, int accum) {
            this.idx = idx;
            this.time = time;
            this.accum = accum;
        }
    }
}