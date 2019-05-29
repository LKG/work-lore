package im.heart;


import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import im.heart.core.utils.EditDistanceUtils;

public class Main {

    public static void main(String[] args) {
        String s1 = "子类可以覆盖父类的成员方法，也可以覆盖父类的成员变量";
        String s2 = "子类不可以覆盖父类的成员方法，也不可以覆盖父类的成员变量";
        s1 = "我";
        s2 = "农山夫";
        double d = CoreSynonymDictionary.similarity(s1, new String(s2));
        System.out.println(d);
        System.out.println(EditDistanceUtils.similarity(s1, s2));
    }
}
