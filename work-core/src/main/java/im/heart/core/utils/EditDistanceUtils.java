package im.heart.core.utils;

import com.hankcs.hanlp.algorithm.EditDistance;

public class EditDistanceUtils extends EditDistance {

    public static Double similarity(String str1, String str2) {
        /**
         *  计算编辑距离
         */
        int distance = EditDistance.compute(str1, str2) + 1;
        return 1.0 / distance;
    }

    public static void main(String[] args) {
//        System.out.println(similarity("SNOWY", "SUNNY"));
//        System.out.println(similarity("adb", "adbsss"));
//        System.out.println(similarity("abdd", "aebdd"));
//        System.out.println(similarity("travelling", "traveling"));
//        System.out.println(similarity("平整场地", "场地"));
//        System.out.println(similarity("平整场地 dd", "场地 dd 平整"));
//        System.out.println(similarity("平整场地", "我爱我d场地"));

        int ld = EditDistance.compute("平整场地", "平整场地");
        System.out.println("ld:"+ld);
        int ld2 = EditDistance.compute("农夫山", "农夫山");
        System.out.println("ld2:"+ld2);
        System.out.println(similarity("平整场地", "平整场地"));
        System.out.println(similarity("山", "农夫山"));
    }
}
