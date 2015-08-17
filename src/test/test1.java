package test;

import java.util.ArrayList;
import java.util.List;

public class test1 {

    public static void main(String[] args) {
        int a = 450;
        int b = 340;
        int c = 238;
        int d = 100;
        int e = 75;
        int f = 70;
        int g = 69;
        int h = 65;
        int i = 50;
        int j = 20;
        List<Integer> list = new ArrayList<Integer>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        list.add(i);
        list.add(j);

        double qz = 70.0;// 标准权重
        double delta = list.get(0) / qz;
        System.out.println("beishu:" + delta);
        for (Integer num : list) {
            System.out.println(Math.round(num / delta));
        }
        System.out.println("***************************************");

        double referDelta = 0.0;// 倍数
        int referys = 0;// 余数
        for (int x = 0; x < list.size(); x++) {
            int num = list.get(x);
            // 计算标准值
            if (x == 0) {
                // 权重值小于70不进行计算//权重值小于70不进行计算
                if (num < 70) {
                    break;
                }
                referDelta = num / qz;
                referys = (int) (num % qz);
            }

            double referNum = (num / referDelta + (num - referys) / Math.round(referDelta)) / 2;
            System.out.println(num + " " + Math.round(referNum));
        }
    }

}
