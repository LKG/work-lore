package im.heart.core.enums;

public enum IdentityType {
    qq("qq","QQ登录"),
    github("github","github登录"),
    osChina("osChina","osChina 登录"),
    weibo("weibo","新浪微博登录"),
    dingtalk("dingtalk","钉钉登录"),
    wechat("wechat","微信登录");
    private IdentityType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String code;
    public String desc;
}
