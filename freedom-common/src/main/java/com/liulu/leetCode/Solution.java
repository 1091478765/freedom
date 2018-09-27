package com.liulu.leetCode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 刘璐 on 2018/9/27.
 */
@Slf4j
public class Solution {

     public class ListNode {
         int val;
         ListNode next;
        ListNode(int x) { val = x; }
 }

    public static int[] twoSum(int[] nums, int target) {
        for(int i= 0 ; i < nums.length-1; i++){
            for (int j = i+1; j< nums.length; j++){
                int total = nums[i]+ nums[j];
                if (total == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j))
                    ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch))
                return false;
            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{2, 7, 11, 15};
        int target = 13;
        nums = twoSum(nums,target);
        System.out.println(nums[0]);
        System.out.println(nums[1]);
    }
}
