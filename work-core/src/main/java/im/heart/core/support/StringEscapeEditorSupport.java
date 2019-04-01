package im.heart.core.support;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author gg
 * @desc 将传递进来的String进行HTML编码，防止XSS攻击 用于防止XSS攻击
 */
public class StringEscapeEditorSupport extends PropertyEditorSupport {
    /**
     * 是否编码HTML
     */
    private boolean escapeHTML = Boolean.TRUE;
    private boolean escapeJavaScript = Boolean.FALSE;

    public StringEscapeEditorSupport() {
        super();
    }

    public StringEscapeEditorSupport(boolean escapeHTML, boolean escapeJavaScript) {
        super();
        this.escapeHTML = escapeHTML;
        this.escapeJavaScript = escapeJavaScript;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
            String value = text;
            //清除不安全链接
            value=Jsoup.clean(value, Whitelist.basic());
            if (escapeHTML) {
                value = HtmlUtils.htmlEscape(value);
            }
            if (escapeJavaScript) {
                value = JavaScriptUtils.javaScriptEscape(value);
            }
            setValue(value);
        }
    }
}