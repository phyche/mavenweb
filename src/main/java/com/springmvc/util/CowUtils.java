package com.springmvc.util;

import java.util.*;

public final class CowUtils {

    static int []signs=new int [52];//标记牌数
    //模拟52张牌
    private static final double[] cards =
            {1.1,2.1,3.1,4.1,5.1,6.1,7.1,8.1,9.1,10.1,11.1,12.1,13.1,
            1.2,2.2,3.2,4.2,5.2,6.2,7.2,8.2,9.2,10.2,11.2,12.2,13.2,
            1.3,2.3,3.3,4.3,5.3,6.3,7.3,8.3,9.3,10.3,11.3,12.3,13.3,
            1.4,2.4,3.4,4.4,5.4,6.4,7.4,8.4,9.4,10.4,11.4,12.4,13.4};

    //jqk的特殊处理,jqk依次为11,12,13，但是计算的时候均为10
    public static int getNum(double a) {
        if (a > 10) {
            return 10;
        }else {
            return (int) Math.floor(a);
        }
    }

    //获取牛（包括是否有牛，牛几，以及手上的牌从小到大排序）
    private static Map getCow(double[] cards) {

        Map map = new HashMap();
        boolean isCrow = false;//是否有牛
        //int cow = (int) (Math.floor(cards[0]) + Math.floor(cards[1]) + Math.floor(cards[2]));//前三位综合
        int cow = getNum(cards[0]) +getNum(cards[1]) + getNum(cards[2]);
        if (cow % 10 == 0) {
            isCrow = true;
        }
        int cowNum = -1;//牛几
        if (isCrow) {
            //cowNum = (int) (Math.floor(cards[3]) + Math.floor(cards[4]));
            cowNum = getNum(cards[3]) +getNum(cards[4]);
            cowNum = cowNum % 10;
            if (cowNum == 0) {
                cowNum = 10;
            }
        }
        map.put("isCrow",isCrow);
        map.put("cowNum",cowNum);

        map.put("sort",getSort(cards));//手上牌排序
        return map;
    }

    //将数组排序（升序）
    public static Map getSort(double[] a) {
        HashMap map = new HashMap();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i); // 将值和下标存入Map
        }

        // 排列
        List list = new ArrayList();
        Arrays.sort(a); // 升序排列

        //输出排序后的数组
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        for (Object object : list) {
            System.out.print(object + ",");
        }
        System.out.println();

        // 查找原始下标
        /*int[] b = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print(map.get(a[i]) + ",");
            b[i] = (int) map.get(a[i]);
        }*/
        Map result = new HashMap();
        result.put("sort1",a);
        //result.put("sort2",b);
        return result;
    }

    //比较两组牌大小
    //0：一样大，1：数组a1大，2：数组a2大
    public static int compare(double[] a1,double[] a2) {

        Map map1 = getCow(a1);
        Map map2 = getCow(a2);

        //是否有牛
        boolean isCrow1 = (boolean) map1.get("isCrow");
        boolean isCrow2 = (boolean) map2.get("isCrow");

        //牛几
        int crowNum1 = (int) map1.get("cowNum");
        int crowNum2 = (int) map2.get("cowNum");

        //排序map
        Map sortMap1 = (Map) map1.get("sort");
        Map sortMap2 = (Map) map2.get("sort");

        //排序数组（带有花色）
        double[] sortNum1 = (double[]) sortMap1.get("sort1");
        double[] sortNum2 = (double[]) sortMap2.get("sort1");

        if (!isCrow1 && !isCrow2) {
            //都没有牛，一样大
            return 0;
        } else if (isCrow1 && !isCrow2) {
            //a1有牛，a2没有牛，a1大
            return 1;
        } else if (!isCrow1 && isCrow2) {
            //a1没有牛，a2有牛，a2大
            return 2;
        } else if (isCrow1 && isCrow2) {
            //都有牛
            if (crowNum1 > crowNum2) {
                return 1;
            } else if (crowNum1 < crowNum2) {
                return 2;
            } else if (crowNum1 == crowNum2) {
                //牛数一样比较最大牌以及花色
                return compareFlower(sortNum1,sortNum2);
            }
        }
        return 0;
    }

    //根据排序后的数组获取花色数组
    public static int[] getFlowerBySort(double[] a1,int[] a2) {
        int[] a = new int[5];
        for (int i = 0;i<5;i++) {
            a[i] = (int) (a1[a2[i]]*10%10);
        }
        return a;
    }

    //牛一样的情况下比较最大牌以及花色
    public static int compareFlower(double[] a1,double[] b1) {

        for (int i = 4; i>=0;i--) {
            //比较最大点数(因为点数后面的小数点代表花色，因此直接比较数的大小就可以)
            //举例：桃5（5.4） 和 方5（5.1）5.4>5.1，即最大数同是5的时候依然 桃>方
            double a = a1[i];
            double b = b1[i];
            if (a>b) {
                return 1;
            } else if (a<b) {
                return 2;
            } else if (a==b) {
                //最大数相同，依次比较后面的。。。
                continue;
            }
        }
        return 0;
    }

    //发牌（52张牌跟人数选择发牌，number代表人数）
    public static double[][] getCards(int number) {
        double[][] a = new double[number][5];
        Random r = new Random();
        //生成number副牌
        for(int j=0;j<number;j++){
            //生成一副牌（5张）
            for(int i=0;i<5;i++){
                //52个里面随机选取一个数作为随机数
                int n1=r.nextInt(52);
                if(signs[n1]==0){
                    //获取数组下标为随机数的值
                    System.out.print(cards[n1]+" ");
                    a[j][i] = cards[n1];
                    signs[n1]=1;//作为标记，代表这个随机数已用
                }
                else{
                    i--;
                }
            }
            System.out.print('\n');
        }
        return a;
    }

    public static void main(String[] args)
    {

        //发牌，number代表有几个人，返回的是一个二维数组，每一行就代表一个人的牌
        double[][] cards = getCards(10);

        //数组说明桃心梅方 小数点后的数字代表花色，依次倒序排：举例：桃，心，梅，方 == 4,3,2,1
        //数组举例：方2桃4梅3心6梅8=={2.1, 5.4, 3.2, 6.3, 8.2}
        //jqk的特殊处理,jqk依次为11,12,13，但是计算的时候均为10
        //double[] a = new double[]{2.1, 5.4, 3.2, 11.3, 8.2};
        //double[] b = new double[]{2.1, 5.4, 3.2, 11.3, 8.3};
        //int c = compare(a,b);
        //System.out.println(c);

        //测试随机发牌，并取第3个和第6个人的牌进行比较
        double[] a = new double[5];
        double[] b = new double[5];
        for(int i=0;i<cards[2].length;i++){//这里获取二维数组第3行的值(取第3个人的牌)
            a[i] = cards[2][i];//赋值给一维数组
        }
        for(int i=0;i<cards[5].length;i++){//这里获取二维数组第6行的值(取第6个人的牌)
            b[i] = cards[5][i];//赋值给一维数组
        }
        //a=autoCow(a);
        //b=autoCow(b);
        //int c = compare(a,b);
        //System.out.println(c);

        double[] d = {10.1,4.3,6.4,4.2,2.2};
        d = autoCow(d);
    }

    //自动组成牛
    public static double[] autoCow(double[] cards){
        int cardsTotal =0;
        for(int i=0;i<cards.length;i++){
            cardsTotal +=getNum(cards[i]);
        }
        System.out.println("cardsTatoal="+cardsTotal);
        double[] sortAferCard = new double[5];
        for(int i=0;i<5;i++ ){
            for(int j=i+1;j<5;j++ ){
                //依次取出两个数，看剩余的三个数能否组成牛
                if((cardsTotal-getNum(cards[i])-getNum(cards[j]))%10==0){
                    sortAferCard = new double[5];
                    for (int k = 0;k<5;k++) {
                        for (int l=0;l<3;l++) {
                            if (k != i && k!= j && String.valueOf(sortAferCard[l]).equals("0.0")) {
                                //将能组成牛的数放入新数组的前三位（可以不需要顺序）
                                sortAferCard[l] = cards[k];
                                break;
                            }
                        }
                    }
                    sortAferCard[3] = cards[i];
                    sortAferCard[4] = cards[j];
                    System.out.println(sortAferCard);
                    return sortAferCard;
                }
            }
        }
        //无法组成牛的时候就直接输出发的牌
        return cards;
    }

    public static double[] getBigCow(double[][] cow) {
        double[] a = new double[5];
        double[] b = new double[5];

        for(int i=0;i<5;i++) {
            a[i] = cow[0][i];
        }
        double[] big  = a;//首先取二维数组的第一行作为最大牛的结果
        for (int i=1;i<cow.length;i++) {
            for(int j=0;j<5;j++) {
                b[j] = cow[i][j];
            }
            int compare = compare(big,b);
            if (compare<2) {
                //a比b小，则最大为b
                big = b;
            } else {
                big = a;
            }
        }
        return big;
    }
}
