package im.heart.core.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author gg
 * @desc session 类型
 */
public enum SessionType {

    DISTRIBUTED_SESSION("distributedSession", "分布式session"),
    JWT_TOKEN("jwtToken", "jwtToken验证");

    SessionType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getText() {
        return text;
    }
    public String getCode() {
        return code;
    }

    public static SessionType fromCode(String code) {
        for (SessionType sessionType : SessionType.values()) {
            if (StringUtils.equals(code, sessionType.getCode())) {
                return sessionType;
            }
        }
        return null;
    }
}
