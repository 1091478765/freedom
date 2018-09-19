package com.liulu.financialUtils;

/**
 * @Description: 等额本息工具类
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/9/19 10:13
 * @Create Author: nevermore
 * @File Name: AverageCapitalPlusInterestUtils
 * @Last version: 2.1.0
 */
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 等额本息还款，也称定期付息，即借款人每月按相等的金额偿还贷款本息，其中每月贷款利息按月初剩余贷款本金计算并逐月结清。把按揭贷款的本金总额与利息总额相加，
 * 然后平均分摊到还款期限的每个月中。作为还款人，每个月还给银行固定金额，但每月还款额中的本金比重逐月递增、利息比重逐月递减。
 * http://finance.sina.com.cn/calc/money_loan.html 与新浪财经给出计算结果一致
 */

public class AverageCapitalPlusInterestUtils {

    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还本金和利息
     *
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalmonth
     *            还款总月数
     * @return 每月偿还本金和利息,不四舍五入，直接截取小数点最后两位
     */
    public static BigDecimal getPerMonthPrincipalInterest(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal monthIncome = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 10, BigDecimal.ROUND_DOWN);
        return monthIncome = monthIncome.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还利息
     *
     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalmonth
     *            还款总月数
     * @return 每月偿还利息
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(double invest, double yearRate, int totalmonth, Integer scale) {
        if (scale == null){
            scale = 2;
        }
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        double monthRate = yearRate/12;
        BigDecimal monthInterest;
        for (int i = 1; i < totalmonth + 1; i++) {
            BigDecimal multiply = new BigDecimal(invest).multiply(new BigDecimal(monthRate));
            BigDecimal sub  = new BigDecimal(Math.pow(1 + monthRate, totalmonth)).subtract(new BigDecimal(Math.pow(1 + monthRate, i-1)));
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 6, BigDecimal.ROUND_DOWN);
            monthInterest = monthInterest.setScale(scale, BigDecimal.ROUND_HALF_UP);
            map.put(i, monthInterest);
        }
        return map;
    }
    /**
     * 等额本息计算获取还款方式为等额本息的每月偿还本金
     * 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalmonth
     *            还款总月数
     * @return 每月偿还本金
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        Map<Integer, BigDecimal> mapPrincipal = new HashMap<Integer, BigDecimal>();
        for(int i=1; i<totalmonth+1; i++) {
            BigDecimal monthIncome = new BigDecimal(invest)
                    .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, i-1)))
                    .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth ) - 1), 10, BigDecimal.ROUND_DOWN);
            mapPrincipal.put(i,monthIncome.setScale(2,BigDecimal.ROUND_HALF_UP));

        }
        return mapPrincipal;
    }

    /**
     * 等额本息计算获取还款方式为等额本息的总利息
     *
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalmonth
     *            还款总月数
     * @return 总利息
     */
    public static double getInterestCount(double invest, double yearRate, int totalmonth) {
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> map = getPerMonthInterest(invest,yearRate,totalmonth,10);
        for (Map.Entry<Integer, BigDecimal> entry : map.entrySet()) {
            count = count.add(entry.getValue());
        }
        count = count.setScale(2,BigDecimal.ROUND_HALF_UP);
        return count.doubleValue();
    }

    /**
     * 应还本金总和
     * @param invest
     *            总借款额（贷款本金）
     * @param yearRate
     *            年利率
     * @param totalmonth
     *            还款总月数
     * @return 应还本金总和
     */
    public static double getPrincipalInterestCount(double invest, double yearRate, int totalmonth) {
        double monthRate = yearRate / 12;
        BigDecimal perMonthInterest = new BigDecimal(invest)
                .multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, totalmonth)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, totalmonth) - 1), 10, BigDecimal.ROUND_DOWN);
        BigDecimal count = perMonthInterest.multiply(new BigDecimal(totalmonth));
        count = count.setScale(2, BigDecimal.ROUND_DOWN);
        return count.doubleValue();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        double invest = 25000; // 本金
        int month = 12;
        double yearRate = 0.15; // 年利率
        BigDecimal perMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, yearRate, month);
        System.out.println("等额本息---每月还款本息：" + perMonthPrincipalInterest);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, yearRate, month,2);
        System.out.println("等额本息---每月还款利息：" + mapInterest);
        Map<Integer, BigDecimal> mapPrincipal = getPerMonthPrincipal(invest, yearRate, month);
        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
        double count = getInterestCount(invest, yearRate, month);
        System.out.println("等额本息---总利息：" + count);
        double principalInterestCount = getPrincipalInterestCount(invest, yearRate, month);
        System.out.println("等额本息---应还本息总和：" + principalInterestCount);
    }

}
