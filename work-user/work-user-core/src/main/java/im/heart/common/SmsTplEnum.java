package im.heart.common;
/**
 *
 * @author gg
 * @desc 短信模板枚举
 */
public enum SmsTplEnum {
    REGISTER("SMS_01","register","用户注册", "register.ftl"),
    FIND_PWD("SMS_02","findPwd","用户密码找回", "findPwd.ftl");

    public String templateId;
    public String name;
    public String description;
    /**
     * 模板路径
     */
    public String templatePath;
    SmsTplEnum(String templateId, String name, String description, String templatePath) {
        this.name = name;
        this.templateId = templateId;
        this.templatePath = templatePath;
        this.description = description;
    }

    public static SmsTplEnum fromName(String name){
        for(SmsTplEnum refer : SmsTplEnum.values()) {
            if (refer.name.equals(name)) {
                return refer;
            }
        }
        return null;
    }
}
