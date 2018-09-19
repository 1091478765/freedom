package com.liulu.financialUtils;

/**
 * @Description: 等额本金工具类
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/9/19 10:22
 * @Create Author: nevermore
 * @File Name: AverageCapitalUtils
 * @Last version: 2.1.0
 */

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AverageCapitalUtils {
    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金和利息
     *
     * 公式：每月偿还本金=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
     *
     * 和新浪财经一致 http://finance.sina.com.cn/calc/money_loan.html
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @param scale 保留位数
     * @return 每月偿还本金和利息
     */
    public static Map<Integer, Double> getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth,Integer scale) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        // 每月本金
        double monthPri = getPerMonthPrincipal(invest, totalMonth,10);
        // 获取月利率
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
            double monthRes = monthPri + (invest - monthPri * (i - 1)) * monthRate;
            monthRes = new BigDecimal(monthRes).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put(i, monthRes);
        }
        return map;
    }
    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还利息
     *
     * 公式：每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        double monthRate = yearRate/12;
        Map<Integer, Double> inMap = new HashMap<Integer, Double>();
        double principal = getPerMonthPrincipal(invest, totalMonth,10);
        Map<Integer, Double> map = getPerMonthPrincipalInterest(invest, yearRate, totalMonth,10);
        for (int i = 1 ; i < totalMonth+1; i++){
            double monthInterest = (invest - principal*(i-1))*monthRate;
            inMap.put(i,new BigDecimal(monthInterest).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return inMap;
    }

    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金
     *
     * 公式：每月应还本金=贷款本金÷还款月数
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param totalMonth
     *            还款总月数
     * @return 每月偿还本金
     */
    public static double getPerMonthPrincipal(double invest, int totalMonth,Integer scale) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), scale, BigDecimal.ROUND_HALF_UP);
        return monthIncome.doubleValue();
    }

    /**
     * 等额本金计算获取还款方式为等额本金的总利息
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalMonth
     *            还款总月数
     * @return 总利息
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = count.add(new BigDecimal(entry.getValue()));
        }
        return count.doubleValue();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 10000; // 本金
        int month = 12;
        double yearRate = 0.15; // 年利率
        Map<Integer, Double> getPerMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month,2);
        System.out.println("等额本金---每月本息：" + getPerMonthPrincipalInterest);
        double benjin = getPerMonthPrincipal(invest, month,2);
        System.out.println("等额本金---每月本金:" + benjin);
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, month);
        System.out.println("等额本金---每月利息:" + mapInterest);

        double count = getInterestCount(invest, yearRate, month);
        System.out.println("等额本金---总利息：" + count);
    }

}
