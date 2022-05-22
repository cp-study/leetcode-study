package leetcode;

import java.util.Stack;

class LC_1776 {
    public double[] getCollisionTimes(int[][] cars) {
        double[] ans = new double[cars.length];
        Stack<CarInfo> stack = new Stack<>();


        for(int i=cars.length-1;i>=0;i--) {
            while(!stack.empty()) {
                CarInfo rightCarInfo = stack.peek();
                if(rightCarInfo.spd >= cars[i][1]) {
                    stack.pop();
                } else {
                    double currCollideTime = (double)(rightCarInfo.pos - cars[i][0])/
                                    (cars[i][1] - rightCarInfo.spd);
                    if(rightCarInfo.collideTime >= currCollideTime) {
                        stack.push(new CarInfo(cars[i][0],cars[i][1], currCollideTime));
                        ans[i] = currCollideTime;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if(stack.empty()) {
                stack.push(new CarInfo(cars[i][0], cars[i][1],Double.MAX_VALUE));
                ans[i] =-1;
            }
        }

        return ans;
    }

    class CarInfo {
        int pos, spd;
        double collideTime;

        public CarInfo(int pos, int spd, double collideTime) {
            this.pos = pos;
            this.spd = spd;
            this.collideTime = collideTime;
        }
    }
}