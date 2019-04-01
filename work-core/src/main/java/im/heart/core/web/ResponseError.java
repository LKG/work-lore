package im.heart.core.web;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.web.enums.WebError;
import lombok.Data;

/**
 *
 * @author gg
 * @desc 统一返回错误信息
 */
@Data
public class ResponseError {
    @JSONField(name = "error")
    private String name;

    @JSONField(name = "error_code")
    private String code;
    @JSONField(name = "error_description")
    private String description;

    @JSONField(name = "error_url")
    private String errorUrl;

    public ResponseError(WebError webError) {
        this(webError.getCode(),webError.getName(),webError.getDescription());
    }

    public ResponseError(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public ResponseError(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
