package com.liulu.leetCode;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 刘璐 on 2018/9/27.
 */
@Slf4j
public class Solution {



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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return null;

    }
    public Map<Integer,Integer> addVal(Integer val1, Integer val2){
        Integer total = val1 + val2;
        Integer targetNum = 0;
        Integer targetNum2 = 0;
        if (total > 9){
            targetNum = 1;
            targetNum2 = total%10;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{2, 7, 11, 15};
        int target = 13;
        nums = twoSum(nums,target);
        System.out.println(nums[0]);
        System.out.println(nums[1]);
    }


}
