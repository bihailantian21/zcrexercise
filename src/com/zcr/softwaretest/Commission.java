package com.zcr.softwaretest;

import java.util.Scanner;

/**
 * @author zcr
 * @date 2019/5/9-21:30
 */
public class Commission {
    public static void main(String args[]){
        int locks,stocks,barrels;
        int lockPrice = 45,stocksPrice = 30,barrelPrice = 25;
        int totalLocks = 0,totalStocks = 0,totalBarrel = 0;
        int lockSales,stockSales,barrelSales;
        double sales,commission;

        Scanner sc = new Scanner(System.in);
        locks = sc.nextInt();
        stocks = sc.nextInt();
        barrels = sc.nextInt();

        totalLocks = totalLocks + locks;
        totalStocks = totalStocks + stocks;
        totalBarrel = totalBarrel + barrels;

        lockSales = lockPrice * locks;
        stockSales = stocksPrice * stocks;
        barrelSales = barrelPrice * barrels;

        sales = lockSales + stockSales + barrelSales;

        if (sales > 1800){
            commission = 0.1*1000;
            commission = commission + 0.15*800;
            commission = commission + 0.2*(sales - 1800);
        }else if (sales > 1000){
            commission = 0.1 * 1000;
            commission = commission + 0.15*(sales - 1000);
        }else {
            commission = 0.1*sales;
        }
        System.out.println(commission);





    }



}
