package im.heart.common;
/**
 *
 * @author gg
 * @desc 短信模板枚举
 */
public enum SmsTplEnum {

    REGISTER("SMS_01","370072","用户注册", "register.ftl"),
    FIND_PWD("SMS_02","findPwd","用户密码找回", "findPwd.ftl");
    public String code;
    public String templateId;

    public String description;
    /**
     * 模板路径
     */
    public String templatePath;
    SmsTplEnum( String code, String templateId,String description, String templatePath) {
        this.code = code;
        this.templateId = templateId;
        this.templatePath = templatePath;
        this.description = description;
    }

    public static SmsTplEnum fromCode(String code){
        for(SmsTplEnum refer : SmsTplEnum.values()) {
            if (refer.code.equals(code)) {
                return refer;
            }
        }
        return null;
    }
}
