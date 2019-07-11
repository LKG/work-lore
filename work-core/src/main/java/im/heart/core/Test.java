package im.heart.core;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        String redirectUrl="http://a.com";
        System.out.println(StringUtils.indexOf(redirectUrl,"?"));

        if(StringUtils.indexOf(redirectUrl,"?")!=-1){
            redirectUrl=redirectUrl+"&openId=1";
        }else {
            redirectUrl=redirectUrl+"?openId=1";
        }
        System.out.println(redirectUrl);
    }
}
